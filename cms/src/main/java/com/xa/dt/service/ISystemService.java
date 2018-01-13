package com.xa.dt.service;

import java.util.List;

import com.xa.dt.beans.AccordionBean;
import com.xa.dt.beans.DataGrid;
import com.xa.dt.beans.MenuBean;
import com.xa.dt.beans.MenuTree;
import com.xa.dt.beans.OpResult;
import com.xa.dt.beans.UserBean;

public interface ISystemService {
	
	public OpResult login(String uname, String upwd);
	
	public List<AccordionBean> getAccordion();

	public OpResult insertMenu(MenuBean menu);
	
	public List<MenuTree> getMenuTree(String id);
	
	public DataGrid<MenuBean> getMenuList(int page, int rows, int mid);
	
	public OpResult insertUser(UserBean user);
	
	public OpResult updateUser(UserBean user);
	
	public OpResult deleteUsers(List<Integer> uid);
	
	public UserBean getUser(int uid);
	
	public DataGrid<UserBean> getUsers(int page, int rows, UserBean ub);
	
	public OpResult deleteMenu(List<Integer> mid);
	
	public MenuBean getMenu(int mid);
	
	public OpResult updateMenu(MenuBean mb);
}
