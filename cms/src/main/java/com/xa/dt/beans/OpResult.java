package com.xa.dt.beans;

public class OpResult{

	/**
	 * result三种类型0 正常 1 冲突 2 失败
	 */
	private int result;
	
	private String msg;
	
	private Object obj;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
