package com.xa.dt.service.caller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname HelloService
 * @date 2019-05-07 16:27
 * @version: 1.0
 * @description: TODO
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }
}
