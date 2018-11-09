package com.xa.dz.ims.controller;

import com.alibaba.fastjson.JSONObject;
import com.xa.dz.ims.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Controller
public class PageController {

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("跳转到登录页面！");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("登录");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String pwdTmp = new String(Base64.getDecoder().decode(password));
        JSONObject object = userService.login(userName, pwdTmp);
        if(object.getBoolean("success")) {
            // 设置格式
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            // 创建Cookie
            Cookie cookie = new Cookie("myJavaData", Base64.getEncoder().encodeToString((userName + "-" + password).getBytes()));
            // 有效期,秒为单位
            cookie.setMaxAge(3600);
            // 设置cookie
            response.addCookie(cookie);
            return object;
        } else {
            return object;
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("跳转到首页！");
        return "home";
    }
}
