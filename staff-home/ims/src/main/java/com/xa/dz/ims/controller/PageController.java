package com.xa.dz.ims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        logger.debug("跳转到web项目首页！");
        return "index";
    }
}
