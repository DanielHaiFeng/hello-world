package com.xa.dt.beans;

import java.util.List;

public class DataGrid<E> {

	private List<E> rows;
	
	private int total;

	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
