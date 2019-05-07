package com.xa.dt.service.caller.controller;

import com.xa.dt.service.caller.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname HelloControler
 * @date 2019-05-07 16:27
 * @version: 1.0
 * @description: TODO
 */
@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }
}
