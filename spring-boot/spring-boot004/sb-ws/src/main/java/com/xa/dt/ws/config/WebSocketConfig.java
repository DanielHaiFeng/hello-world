package com.xa.dt.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author DangTing[dangting@boco.com.cn]
 * @CreateDate 2018/12/28 9:39
 * @Description
 * @Copyright Copyright 2018/12/28 9:39 BOCO. All rights reserved
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
