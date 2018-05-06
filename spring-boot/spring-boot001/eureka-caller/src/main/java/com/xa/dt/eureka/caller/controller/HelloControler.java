package com.xa.dt.eureka.caller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xa.dt.eureka.caller.service.HelloService;

@RestController
public class HelloControler {
	
	@Autowired
	HelloService helloService;

	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String name) {
		return helloService.hiService(name);
	}
	
	@RequestMapping(value = "/work", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String work(@RequestParam String name, @RequestParam String jobName) {
		return helloService.work(name, jobName);
	}
}
