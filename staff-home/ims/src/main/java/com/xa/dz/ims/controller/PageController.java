package com.xa.dz.ims.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("跳转到登录页面！");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(Model model) {
        logger.debug("登录");
        JSONObject object = new JSONObject();
        object.put("success", true);
        object.put("message", "");
        return object;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        logger.debug("跳转到首页！");
        return "home";
    }
}
