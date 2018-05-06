package com.xa.dt.eureka.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {
	
	private static Logger log = LoggerFactory.getLogger(EurekaServer.class);

	public static void main(String[] args) {
		log.info("启动eureka server");
		SpringApplication.run(EurekaServer.class, args);
	}
}
