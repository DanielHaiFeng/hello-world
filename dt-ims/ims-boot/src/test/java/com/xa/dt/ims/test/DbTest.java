package com.xa.dt.ims.test;

import com.alibaba.fastjson.JSON;
import com.xa.dt.ims.ImsBootApplication;
import com.xa.dt.ims.db.database.dao.UserMapper;
import com.xa.dt.ims.db.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ImsBootApplication.class})
@Slf4j
public class DbTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testQueryUser() {
        List<User> users = userMapper.selectByExample(null);
        log.info("获取到的用户信息{}", JSON.toJSONString(users));
    }
}
