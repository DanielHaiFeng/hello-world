package com.xa.dt.beans;

import java.io.Serializable;
import java.util.List;

public class MenuTree implements Serializable {

	private static final long serialVersionUID = -977419639386623569L;

	private String id;

	private String text;

	private String state;

	private String iconCls;
	
	private TreeAttributes attributes;

	private List<MenuTree> children;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public TreeAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(TreeAttributes attributes) {
		this.attributes = attributes;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

}
