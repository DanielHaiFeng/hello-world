package com.xa.dt.dao;

import java.util.List;

import com.xa.dt.beans.MenuBean;

public interface MenuDao {
	
    int deleteByPrimaryKey(Integer mid);
    
    int deleteByIds(List<Integer> mids);

    int isMenuExist(String mname);
    
    int insert(MenuBean record);

    int insertSelective(MenuBean record);

    MenuBean selectByPrimaryKey(Integer mid);
    
    MenuBean getMenuByName(String name);
    
	List<MenuBean> getMenus(Integer pid);
    
    List<MenuBean> getMenuList(MenuBean record);
    
	int getMenuCount(MenuBean record);
    
    int updateByPrimaryKeySelective(MenuBean record);

    int updateByPrimaryKey(MenuBean record);
}