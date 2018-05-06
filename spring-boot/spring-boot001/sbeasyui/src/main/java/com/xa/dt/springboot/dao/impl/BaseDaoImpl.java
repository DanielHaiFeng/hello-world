package com.xa.dt.springboot.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.xa.dt.springboot.bean.User;
import com.xa.dt.springboot.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<User> queryUsers() {
		String sql = "select id, name, password, age from user_infor";
		return jdbcTemplate.query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rest, int arg1) throws SQLException {
				User u = new User();
				u.setId(rest.getInt(1));
				u.setName(rest.getString(2));
				u.setPassword(rest.getString(3));
				u.setAge(rest.getInt(4));
				return u;
			}

		});
	}

}
