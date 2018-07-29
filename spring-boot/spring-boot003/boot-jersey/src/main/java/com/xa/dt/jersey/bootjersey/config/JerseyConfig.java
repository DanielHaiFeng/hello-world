package com.xa.dt.jersey.bootjersey.config;

import com.xa.dt.jersey.bootjersey.resource.Demo;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        //构造函数，在这里注册需要使用的内容，（过滤器，拦截器，API等）
        //类注册方式
        register(Demo.class);
        //包注册方式
        //packages("com.xa.dt.jersey.bootjersey.resource");
    }

}
