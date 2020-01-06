package com.xa.dt.ims;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = {"com.xa.dt.ims.db.database.dao"})
public class ImsBootApplication {

    public static void main(String[] args) {
        log.info("启动ims应用开始");
        SpringApplication.run(ImsBootApplication.class, args);
        log.info("启动ims应用成功");
    }
}
