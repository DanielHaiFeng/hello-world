package com.xa.dt.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	Logger log = LoggerFactory.getLogger(TestController.class);
	
    @GetMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }
    
    @GetMapping("/helloworld2")
    public String helloworld2() {
    	log.debug("debug级别测试");
    	log.info("infor级别测试");
        return "helloworld2";
    }
}