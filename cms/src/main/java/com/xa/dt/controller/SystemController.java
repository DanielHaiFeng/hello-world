package com.xa.dt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xa.dt.beans.AccordionBean;
import com.xa.dt.beans.DataGrid;
import com.xa.dt.beans.MenuBean;
import com.xa.dt.beans.MenuTree;
import com.xa.dt.beans.OpResult;
import com.xa.dt.beans.UserBean;
import com.xa.dt.service.ISystemService;

@Controller
public class SystemController {
	
	private static Logger logger = LoggerFactory.getLogger(SystemController.class);

	@Resource(name="systemService")
	ISystemService systemService;
	
	@RequestMapping("/regUser")
	@ResponseBody 
	public OpResult regUser(UserBean ub){
		return systemService.insertUser(ub);
	}
	
	@RequestMapping("/login")
	@ResponseBody 
	public OpResult login(@RequestParam("uname") String uname,@RequestParam("upwd") String upwd){
		return systemService.login(uname, upwd);
	}
	
	@RequestMapping("/getAccordion")
	@ResponseBody 
	public List<AccordionBean> getAccordion(){
		return systemService.getAccordion();
	}
	
	@RequestMapping("/insertUser")
	@ResponseBody  
	public OpResult insertUser(UserBean ub) {
		logger.info("添加用户 {} ", ub.getUname());
		return systemService.insertUser(ub);
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody  
	public OpResult deleteUser(@RequestBody List<Integer> uidlist) {
		return systemService.deleteUsers(uidlist);
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody  
	public OpResult updateUser(UserBean ub) {
		logger.info("编辑用户 {} ", ub.getUname());
		return systemService.updateUser(ub);
	}
	
	@RequestMapping("/getUser")
	@ResponseBody  
	public UserBean getUser(@RequestParam int uid) {
	    return systemService.getUser(uid);
	}
	
	@RequestMapping("/getUsers")
	@ResponseBody  
	public DataGrid<UserBean> getUsers(String page, String rows, UserBean ub) {
		logger.info("请求第{}页的用户信息", page);
		return systemService.getUsers(Integer.parseInt(page), Integer.parseInt(rows), ub);
	}
	
	@RequestMapping("/getMenuTree")
	@ResponseBody  
	public List<MenuTree> getMenuTree(@RequestParam String id) {
	    return systemService.getMenuTree(id);
	}
	
	@RequestMapping("/getMenuList")
	@ResponseBody  
	public DataGrid<MenuBean> getMenuList(String page, String rows, int mid) {
		logger.info("请求第{}页的菜单信息", page);
	    return systemService.getMenuList(Integer.parseInt(page), Integer.parseInt(rows), mid);
	}
}
