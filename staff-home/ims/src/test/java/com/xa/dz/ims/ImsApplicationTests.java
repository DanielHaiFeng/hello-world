package com.xa.dz.ims;

import com.alibaba.fastjson.JSONObject;
import com.xa.dz.ims.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImsApplicationTests {

    private final static Logger logger = LoggerFactory.getLogger(ImsApplicationTests.class);

    @Autowired
    UserService userService;

    @Test
    public void testPageUser() {
        logger.debug(JSONObject.toJSONString(userService.pageUser(1, 4, null)));
    }

}
