package com.xa.dt.eureka.provider.bean;

import java.io.Serializable;

public class Worker implements Serializable {

	private static final long serialVersionUID = 8966518448271256940L;
	
	private Integer id;
	
	private String name;
	
	private String jobName;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
}
