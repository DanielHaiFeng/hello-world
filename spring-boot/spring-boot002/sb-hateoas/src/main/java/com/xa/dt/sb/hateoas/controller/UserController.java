package com.xa.dt.sb.hateoas.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xa.dt.sb.hateoas.bean.User;
import com.xa.dt.sb.hateoas.dao.UserList;

@RestController
public class UserController {

	@RequestMapping(method = RequestMethod.GET, value = "/user/list")
	public HttpEntity<UserList> list(@RequestParam(value = "name", required = false) String name) {

		User[] users = { new User(1, "jack", "jack T"), new User(2, "tom", "tom C") };
		UserList list = new UserList(Arrays.asList(users));

		list.add(linkTo(methodOn(UserController.class).list(name)).withSelfRel());
		list.add(linkTo(methodOn(UserController.class).get("")).withRel("get"));
		list.add(linkTo(methodOn(UserController.class).insert(null)).withRel("new"));
		list.add(linkTo(methodOn(UserController.class).update("")).withRel("update"));

		return new ResponseEntity<UserList>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public HttpEntity<User> get(@PathParam(value = "uid") String uid) {

		User user = new User(1, "jack", "jack T");

		user.add(linkTo(methodOn(UserController.class).get(uid)).withSelfRel());
		user.add(linkTo(methodOn(UserController.class).insert(null)).withRel("new"));
		user.add(linkTo(methodOn(UserController.class).update(uid)).withRel("update"));
		user.add(linkTo(methodOn(UserController.class).list("")).withRel("collection"));

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/new")
	@ResponseStatus(HttpStatus.CREATED)
	public HttpEntity<User> insert(@RequestBody User auser) {
		String code = auser.getCode();
		String name = auser.getName();

		User user = new User(1000, code, name);

		user.add(linkTo(methodOn(UserController.class).insert(null)).withSelfRel());
		user.add(linkTo(methodOn(UserController.class).get("")).withRel("get"));
		user.add(linkTo(methodOn(UserController.class).update("")).withRel("update"));
		user.add(linkTo(methodOn(UserController.class).list("")).withRel("collection"));

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/user/{id}")
	public HttpEntity<User> update(@PathParam(value = "uid") String uid) {

		User user = new User(1, "jack", "jack T");

		user.add(linkTo(methodOn(UserController.class).update(uid)).withSelfRel());
		user.add(linkTo(methodOn(UserController.class).get(uid)).withRel("get"));
		user.add(linkTo(methodOn(UserController.class).insert(null)).withRel("new"));
		user.add(linkTo(methodOn(UserController.class).list("")).withRel("collection"));

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}