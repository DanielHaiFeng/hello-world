package com.xa.dt.hystrix.service.impl;

import org.springframework.stereotype.Component;
import com.xa.dt.hystrix.service.SchedualServiceHi;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}