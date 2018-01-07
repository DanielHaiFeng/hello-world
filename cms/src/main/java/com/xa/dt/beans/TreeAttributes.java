package com.xa.dt.beans;

import java.io.Serializable;

public class TreeAttributes implements Serializable{

	private static final long serialVersionUID = 6581859645249098942L;
	
	private int mlevel;

	public int getMlevel() {
		return mlevel;
	}

	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}
}
