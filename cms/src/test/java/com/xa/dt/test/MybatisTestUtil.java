package com.xa.dt.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xa.dt.beans.DataGrid;
import com.xa.dt.beans.MenuBean;
import com.xa.dt.beans.UserBean;
import com.xa.dt.service.ISystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context.xml", "classpath*:/spring-mybatis.xml" })
public class MybatisTestUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MybatisTestUtil.class);

	@Resource(name = "systemService")
	ISystemService iSystemService;

	@Test
	public void testAddMenu() {
		MenuBean menu = new MenuBean();
		menu.setMlevel(2);
		menu.setMicon("icon-role");
		menu.setPid(1);
		menu.setMname("dtTest");
		menu.setUrl("pages/index.html");
		System.out.println(iSystemService.insertMenu(menu));
	}
	
	@Test
	public void testAddUser() {
		for (int i = 0; i < 10; i++) {
			UserBean ub = new UserBean();
			ub.setUname("admin" + (i + 1));
			ub.setUpwd("admin");
			ub.setAddress("陕西省西安市丈八四路" + i + "号");
			ub.setCellphone("1350213547" + i);
			iSystemService.insertUser(ub);
		}
//		UserBean ub = new UserBean();
//		ub.setUname("dangt");
//		ub.setUpwd("admin");
//		ub.setAddress("陕西省西安市科技三路1号");
//		ub.setCellphone("18704215361");
//		iSystemService.insertUser(ub);
	}
	
	@Test
	public void testGetUsers() {
		UserBean ub = new UserBean();
		DataGrid<UserBean> dg = iSystemService.getUsers(0, 5, ub);
		System.out.println(dg.getRows().get(0).getAddress());
		logger.info("获取到的行数：{}", dg.getRows().size());
		logger.info("总行数：{} ", dg.getTotal());
		for (UserBean utb : dg.getRows()) {
			logger.info("用户 {} 的地址为 {}", utb.getUname(), utb.getAddress());
		}
	}
}
