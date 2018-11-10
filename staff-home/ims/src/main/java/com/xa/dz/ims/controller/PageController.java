package com.xa.dz.ims.controller;

import com.alibaba.fastjson.JSONObject;
import com.xa.dz.ims.model.Accordion;
import com.xa.dz.ims.service.AccordionService;
import com.xa.dz.ims.service.UserService;
import com.xa.dz.ims.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    UserService userService;

    @Autowired
    AccordionService accordionService;

    @Autowired
    Environment environment;

    @Autowired
    Base64 base64;

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
        String pwdTmp = base64.getFromBase64(password);
        JSONObject object = userService.login(userName, pwdTmp);
        if(object.getBoolean("success")) {
            // 设置格式
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            // 创建Cookie
            String userCookie = base64.getBase64(userName + "-" + pwdTmp);
            Cookie cookie = new Cookie("myJavaData", userCookie);
            // 有效期,秒为单位
            cookie.setMaxAge(3600);
            // 设置cookie
            response.addCookie(cookie);

            HttpSession session = request.getSession();
            session.setAttribute(environment.getProperty("session.username"), userName);
            return object;
        } else {
            return object;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("跳转到首页！");
        return "home";
    }

    @RequestMapping(value = "/getAccordion", method = RequestMethod.POST, consumes = {"application/json;charset=utf-8"})
    @ResponseBody
    public List<Accordion> getAccordion(@RequestBody Map<String, String> bodyMap) {
        String userName = bodyMap.get("userName");
        return accordionService.getAccordion(userName);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("跳转到用户管理页面！");
        return "pages/user";
    }
}
