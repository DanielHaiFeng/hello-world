package com.xa.dt.nacos.demo;

import com.xa.dt.nacos.demo.service.LoginService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        LoginService bean = context.getBean(LoginService.class);
        bean.sayHi("danielt");
    }
}
