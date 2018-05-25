package com.xa.dt.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignServer {
	
	private static Logger log = LoggerFactory.getLogger(FeignServer.class);
	
	/*
	 * ProviderServer can be ran more times with different server.port form mock many server instance
	 * https://blog.csdn.net/forezp/article/details/69808079
	 */
	
	public static void main(String[] args) {
		log.info("启动Feign server");
		SpringApplication.run(FeignServer.class, args);
	}
	
}
