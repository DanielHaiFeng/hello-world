package com.xa.dt.service.hystrix.controller;

import com.xa.dt.service.hystrix.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname HelloControler
 * @date 2019-05-07 17:32
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
