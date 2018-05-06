package com.xa.dt.springboot.bean;

import java.io.Serializable;

public class ComboBox implements Serializable {

	public ComboBox() {
		super();
	}

	private static final long serialVersionUID = -8949366958006438377L;
	
	private String id;
	
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
