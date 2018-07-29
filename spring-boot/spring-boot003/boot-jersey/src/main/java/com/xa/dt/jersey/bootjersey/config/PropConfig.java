package com.xa.dt.jersey.bootjersey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:boco-define.properties","classpath:abc.properties"},
        ignoreResourceNotFound = true,encoding = "utf-8")
public class PropConfig {

    @Value("${boco.name}")
    private String name;

    @Value("${boco.local}")
    private String local;

    @Value("${ceshi}")
    private String ceshi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCeshi() {
        return ceshi;
    }

    public void setCeshi(String ceshi) {
        this.ceshi = ceshi;
    }
}
