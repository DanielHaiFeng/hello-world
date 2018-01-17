package com.xa.dt.dao;

import com.xa.dt.beans.AuthorizeBean;

public interface AuthorizeDao {
	
    int insert(AuthorizeBean record);

    int insertSelective(AuthorizeBean record);
    
    int deleteAuthorize(int uid);
    
    int deleteAuthorizeByMid(int mid);
    
}