package com.xa.dt.sb.hateoas.bean;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User extends ResourceSupport {

	private Integer uid;

	private final String code;

	private final String name;

	@JsonCreator
	public User(@JsonProperty("id") Integer uid, @JsonProperty("code") String code, @JsonProperty("name") String name) {
		this.uid = uid;
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public Integer getUid() {
		return uid;
	}

}