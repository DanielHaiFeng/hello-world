package com.xa.dt.springboot.dao;

import java.util.List;
import com.xa.dt.springboot.bean.User;

public interface BaseDao {

	List<User> queryUsers();
}
