package com.xa.dt.springboot.bean;

import java.io.Serializable;
import java.util.List;

public class DataGrid implements Serializable {

	private static final long serialVersionUID = -3488318729102945712L;

	public DataGrid() {
		super();
	}

	private long total;
	
	private List<?> rows;
	
	private boolean isValidate;
	
	private String msg;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
