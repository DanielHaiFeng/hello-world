package com.xa.dt.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname NacosProducerController
 * @date 2019-05-29 10:49
 * @version: 1.0
 * @description: TODO
 */
@RestController
public class NacosProducerController {

    @Value("${server.port}")
    private Integer port;

    /**
     * 服务接口
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String sayHello(@RequestParam("name") String name) {
        return "hello ---> " + name + " port -->" + port;
    }
}
