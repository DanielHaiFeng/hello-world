package com.xa.dt.dao;

import java.util.List;

import com.xa.dt.beans.UserBean;

public interface UserDao {
	
	int isLogin(String uname, String upwd);
	
    int deleteByPrimaryKey(Integer uid);
    
    int deleteByIds(List<Integer> uids);
    
    int isUserExist(String uname);
    
    int isUserExistForEdit(String uname, int uid);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    UserBean selectByPrimaryKey(Integer uid);
    
    List<UserBean> getUsers(UserBean record);
    
    int getUserCount(UserBean record);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);
}