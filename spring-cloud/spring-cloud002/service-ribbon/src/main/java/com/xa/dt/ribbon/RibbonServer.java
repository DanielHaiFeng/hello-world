package com.xa.dt.ribbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class RibbonServer {
	
	private static Logger log = LoggerFactory.getLogger(RibbonServer.class);
	
	/*
	 * ProviderServer can be ran more times with different server.port form mock many server instance
	 * https://blog.csdn.net/forezp/article/details/69788938
	 */
	
	public static void main(String[] args) {
		log.info("启动Ribbon server");
		SpringApplication.run(RibbonServer.class, args);
	}
	
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
