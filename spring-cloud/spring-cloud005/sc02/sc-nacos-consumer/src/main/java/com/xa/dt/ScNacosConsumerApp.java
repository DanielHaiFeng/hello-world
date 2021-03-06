package com.xa.dt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname ScNacosConsumerApp
 * @date 2019-05-29 11:01
 * @version: 1.0
 * @description: 服务消费者
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ScNacosConsumerApp {

    private static final Logger logger = LoggerFactory.getLogger(ScNacosConsumerApp.class);

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    // 添加负载均衡支持，很简单，只需要在RestTemplate上添加@LoadBalanced注解，那么RestTemplate即具有负载均衡的功能,如果不加@LoadBalanced注解的话，会报java.net.UnknownHostException:springboot-h2异常，此时无法通过注册到Nacos Server上的服务名来调用服务，因为RestTemplate是无法从服务名映射到ip:port的，映射的功能是由LoadBalancerClient来实现的。
    public RestTemplate restTemplate() {
        return builder.build();
    }


    public static void main(String[] args) {
        SpringApplication.run(ScNacosConsumerApp.class, args);
        logger.info("服务消费者已启动");
    }
}
