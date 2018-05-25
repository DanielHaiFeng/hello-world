package com.xa.dt.springboot.bean;

import java.io.Serializable;

public class Article implements Serializable {

	private static final long serialVersionUID = 5972678084127707696L;

	private int id;
	
	private String title;
	
	private String summary;
	
	private int status;
	
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", summary=" + summary + ", status=" + status + ", type=" + type + "]";
	}
}
