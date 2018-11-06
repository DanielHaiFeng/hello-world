package com.xa.dt.springboot.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xa.dt.springboot.App;
import com.xa.dt.springboot.bean.Article;
import com.xa.dt.springboot.dao.BaseDao;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class BaseDaoTest {
	
	Logger log = LoggerFactory.getLogger(BaseDaoTest.class);

    @Autowired
    private BaseDao baseDao;
 
    @Test
    public void testQuery() {
    	List<Article> list = baseDao.query(null);
    	log.info("获取到的文章:{}", list);
    }
}
