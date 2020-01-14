package com.xa.dt.nacos.demo;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@NacosPropertySource(dataId = "nacos-config-dangt", autoRefreshed = true)
@RestController
public class NacosDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(NacosDemoApplication.class);

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(NacosDemoApplication.class, args);
	}

	@NacosValue(value = "${nacos.test.propertie}", autoRefreshed = true)
	private String testProperties;

	@GetMapping("/test")
	public String test() {
		logger.info("other config[{}]", environment.getProperty("ceshi.test.properties"));
		return testProperties;
	}
}
