package com.xa.dt.nacos.demo.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.context.annotation.Configuration;

/**
 * @author DangTing
 * @classname NacosConfiguration
 * @date 2019-05-28 17:13
 * @version: 1.0
 */
@Configuration
@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
public class NacosConfiguration {
}
