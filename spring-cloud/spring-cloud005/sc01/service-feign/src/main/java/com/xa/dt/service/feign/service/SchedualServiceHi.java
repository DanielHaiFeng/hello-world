package com.xa.dt.service.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname SchedualServiceHi
 * @date 2019-05-07 17:04
 * @version: 1.0
 * @description: TODO
 */
@FeignClient(value = "service-hi")
public interface SchedualServiceHi {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
