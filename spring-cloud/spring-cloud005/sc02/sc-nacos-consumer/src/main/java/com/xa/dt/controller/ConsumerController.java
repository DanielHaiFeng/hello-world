package com.xa.dt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname ConsumerController
 * @date 2019-05-29 11:02
 * @version: 1.0
 * @description: 服务消费者
 */
@RestController
public class ConsumerController {

    private static final String SERVICE_NAME = "sc-nacos-provider";

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances(SERVICE_NAME);
    }

    /**
     * 消费服务
     */
    @RequestMapping("/callSayHello")
    public String services(@RequestParam("name") String name) {
        ServiceInstance serviceInstance = (ServiceInstance) discoveryClient.getInstances(SERVICE_NAME).get(0);
        String uri = serviceInstance.getUri().toString();
        String callServiceResult = new RestTemplate().getForObject(uri + "/hello?name="+name, String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }
}
