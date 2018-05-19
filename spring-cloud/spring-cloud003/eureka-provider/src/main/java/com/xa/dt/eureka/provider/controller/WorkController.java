package com.xa.dt.eureka.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xa.dt.eureka.provider.bean.Worker;

@RestController
public class WorkController {
	
	private static Logger log = LoggerFactory.getLogger(WorkController.class);

	@Value("${server.port}")
	String port;
	
	@RequestMapping(value = "/work", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Worker work(@RequestParam String name, @RequestParam String jobName) {
		Worker w = new Worker();
		w.setId(1);
		w.setName(name);
		w.setJobName(jobName);
		log.info("worker [{}] will do the job [{}] on port[{}]", name, jobName, port);
		return w;
	}
}
