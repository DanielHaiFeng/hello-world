package com.xa.dt.sb.hateoas.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;
import com.xa.dt.sb.hateoas.bean.User;

public class UserList extends ResourceSupport {

	private List<User> list;

	public UserList(Collection<User> users) {
		this.list = new ArrayList<User>();
		this.list.addAll(users);
	}

	/**
	 * @return the code
	 */
	public List<User> getUserList() {
		return list;
	}
}