package com.xa.dz.ims.filter;

import com.alibaba.fastjson.JSONObject;
import com.xa.dz.ims.service.UserService;
import com.xa.dz.ims.service.impl.UserServiceImpl;
import org.apache.commons.lang.ObjectUtils;
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

import com.xa.dz.ims.utils.Base64;

/**
 * @Author DangTing[dangting@boco.com.cn]
 * @CreateDate 2018/11/9 14:07
 * @Description
 * @Copyright Copyright 2018/11/9 14:07 Daniel. All rights reserved
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
        String sessionUserNameKey = environment.getProperty("session.username");

        logger.debug("访问的地址[{}]", uri);

        if (!isNeedFilter(uri, rootPath)) {
            chain.doFilter(req, rep);
        } else {
            if (isLogin(request, sessionUserNameKey)) {
                if (uri.equals(rootPath + "/index")) {
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
                    if (uri.equals(rootPath + "/index")) {
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

    private boolean isLogin(HttpServletRequest request, String sessionUserNameKey) {
        Cookie[] cookies = request.getCookies();
        UserService userService = wac.getBean(UserServiceImpl.class);
        Base64 base64 = wac.getBean(Base64.class);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cName = cookie.getName();
                String cVal = cookie.getValue();
                logger.debug("Cookie的key[{}] value[{}]", cName, cVal);
                String upVal = base64.getFromBase64(cVal);
                logger.debug("解密后字符串[{}]", upVal);
                if (cName.equals("JSESSIONID") || !upVal.contains("-")) {
                    continue;
                }
                String[] userPwdTmp = upVal.split("-");
                String userName = userPwdTmp[0];
                String password = userPwdTmp[1];
                JSONObject object = userService.login(userName, password);
                if (object.getBoolean("success")) {
                    //浏览器关闭之后，再次打开浏览器，虽然cookie还在，但是session已经是另外一个了，其中的userName已经不存在了；
                    //如果不添加session中的判断，当服务器重启之后，只要不清除cookie，那么再次访问/ims/index的话，就会直接进入到首页；
                    String sessionUserName = ObjectUtils.toString(request.getSession().getAttribute(sessionUserNameKey));
                    if (sessionUserName.equals(userName)) {
                        return true;
                    }
                } else {
                    logger.error("用户[" + userName + "]登录验证失败，原因[{}]", object.get("message"));
                }
            }
        }
        return false;
    }

    public boolean isNeedFilter(String uri, String rootPath) {
        String fUri = uri.split(";jsessionid=")[0];
        boolean flag = true;
        if (fUri.endsWith(".js") || fUri.endsWith(".css") || fUri.endsWith(".png") || fUri.endsWith(".gif")) {
            flag = false;
        } else {
            for (String includeUrl : includeUrls) {
                if (fUri.equals(rootPath + includeUrl)) {
                    flag = false;
                }
            }
        }
        logger.debug("检查URL[{}]是否需要过滤[{}]", fUri, flag);
        return flag;
    }
}
