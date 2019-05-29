package com.xa.dt.nacos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname ScNacosProviderApp
 * @date 2019-05-29 10:46
 * @version: 1.0
 * @description: 服务提供者启动类
 */
//开启服务发现
@EnableDiscoveryClient
@SpringBootApplication
public class ScNacosProviderApp {

    private static final Logger logger = LoggerFactory.getLogger(ScNacosProviderApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ScNacosProviderApp.class, args);
        logger.info("服务提供者已启动");
    }
}
