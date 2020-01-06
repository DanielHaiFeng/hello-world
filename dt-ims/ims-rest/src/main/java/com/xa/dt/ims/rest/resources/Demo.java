package com.xa.dt.ims.rest.resources;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Path("/demo")
@Slf4j
public class Demo {

    //path注解指定路径,get注解指定访问方式,produces注解指定了返回值类型，这里返回JSON
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject get(@QueryParam("name") String userName) {
        log.info("用户[{}]访问get方法", userName);
        JSONObject obj = new JSONObject();
        obj.put("success", true);
        obj.put("message", "hello " + userName);
        log.info("用户[{}]获得反馈[{}]", userName, obj);
        return obj;
    }
}
