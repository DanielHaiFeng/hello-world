package com.xa.dt.beans;

//作为分页传入参数时使用
public class CommonBean {

	public int pageSize;

	public int start;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setPageInfo(int page, int pageSize) {
		this.setPageSize(pageSize);
		this.setStart((page-1) * pageSize);
	}
}
