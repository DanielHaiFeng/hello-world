package com.xa.dt.jersey.bootjersey.resource;

import com.xa.dt.jersey.bootjersey.beans.City;
import com.xa.dt.jersey.bootjersey.beans.Token;
import com.xa.dt.jersey.bootjersey.beans.TokenUrlParam;
import com.xa.dt.jersey.bootjersey.beans.UserBean;
import com.xa.dt.jersey.bootjersey.config.BocoProperties;
import com.xa.dt.jersey.bootjersey.config.PropConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/demo")
public class Demo {

    @Autowired
    private BocoProperties properties;

    @Autowired
    private PropConfig propConfig;

    private final static Logger logger = LoggerFactory.getLogger(Demo.class);


    //path注解指定路径,get注解指定访问方式,produces注解指定了返回值类型，这里返回JSON
    @Path("/city")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public City get() {
        City city = new City();
        city.setId(1L);
        city.setCityName("beijing");
        city.setCityCode("001");
        logger.info("返回消息[{}]", city.toString());
        logger.info("获取属性文件中的属性[{}]", properties.getName());
        logger.info("获取属性文件中的属性[{}]", propConfig.getName());
        logger.info("获取属性文件中的属性[{}]", propConfig.getCeshi());
        return city;
    }

    @Path("/token")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Token token(@BeanParam TokenUrlParam tokenUrlParam) {
        Token token = new Token();
        token.setResult("true");
        token.setToken(tokenUrlParam.getSystemId()+"---"+tokenUrlParam.getSystemKey());
        logger.info("返回消息[{}]", token.toString());
        return token;
    }


    @Path("/acceptBeanString")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String acceptBeanString(String userBean) {
        return userBean;
    }

    @Path("/acceptBean")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserBean acceptBean(@RequestBody UserBean userBean) {
        userBean.setId(userBean.getId() + " test");
        userBean.setUserName(userBean.getUserName() + " test");
        return userBean;
    }

    @Path("/acceptBeanAndUrl")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserBean acceptBeanAndUrl(@BeanParam TokenUrlParam tokenUrlParam, @RequestBody UserBean userBean) {
        userBean.setId(userBean.getId() + " - " + tokenUrlParam.getSystemId());
        userBean.setUserName(userBean.getUserName() + " - " + tokenUrlParam.getSystemKey());
        return userBean;
    }
}
