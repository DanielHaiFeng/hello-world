package com.xa.dt.eureka.caller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class CallerServer {
	
	/*
	 * access url1 : http://localhost:8764/work?name=tianweiyan&jobName=wash
	 * access url2 : http://localhost:8764/hi?name=tianweiyan
	 * ProviderServer can be ran more times with different server.port form mock many server instance
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(CallerServer.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
