package com.xa.dt.ims.rest;

import com.xa.dt.ims.rest.resources.Demo;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Demo.class);
    }
}