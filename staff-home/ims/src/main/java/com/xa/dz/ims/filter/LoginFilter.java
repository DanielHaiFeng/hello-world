package com.xa.dz.ims.filter;

import com.alibaba.fastjson.JSONObject;
import com.xa.dz.ims.service.UserService;
import com.xa.dz.ims.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * @Author DangTing[dangting@boco.com.cn]
 * @CreateDate 2018/11/9 14:07
 * @Description
 * @Copyright Copyright 2018/11/9 14:07 BOCO. All rights reserved
 */
@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    private WebApplicationContext wac;

    String NO_LOGIN = "您还未登录";

    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/login"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        wac = (WebApplicationContext) filterConfig.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        String uri = request.getRequestURI();
        Environment environment = wac.getEnvironment();
        String rootPath = environment.getProperty("server.context-path");

        logger.debug("访问的地址[{}]", uri);

        if (!isNeedFilter(uri, rootPath)) {
            chain.doFilter(req, rep);
        } else {
            if (isLogin(request)) {
                if (uri.equals(rootPath+"/index")) {
                    //如果访问的是登录页面，并且已经登录，转向到首页
                    //request.getRequestDispatcher("/home").forward(request, response);
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    chain.doFilter(req, rep);
                }
            } else {
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if (requestType != null && "XMLHttpRequest".equals(requestType)) {
                    response.getWriter().write(this.NO_LOGIN);
                } else {
                    if (uri.equals(rootPath+"/index")) {
                        chain.doFilter(req, rep);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/index");
                    }
                }
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        UserService userService = wac.getBean(UserServiceImpl.class);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cVal = cookie.getValue();
                logger.debug("Cookie的key[{}] value[{}]", cookie.getName(), cVal);
                String userPwd = new String(Base64.getDecoder().decode(cVal));
                String[] userPwdTmp = userPwd.split("-");
                String userName = userPwdTmp[0];
                String password = userPwdTmp[1];
                JSONObject object = userService.login(userName, password);
                if(object.getBoolean("success")) {
                    return true;
                } else {
                    logger.error("用户["+userName+"]登录验证失败，原因[{}]", object.get("message"));
                }
            }
        }
        return false;
    }

    public boolean isNeedFilter(String uri, String rootPath) {
        if (uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".gif")) {
            return false;
        }
        for (String includeUrl : includeUrls) {
            if (uri.equals(rootPath + includeUrl)) {
                return false;
            }
        }
        return true;
    }
}
