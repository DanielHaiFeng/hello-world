package com.xa.dt.beans;

import java.io.Serializable;
import java.util.List;

public class AccordionBean implements Serializable{

	private static final long serialVersionUID = 8407803371456627224L;

	private int menuid;
	
	private String icon;
	
	private String menuname;
	
	private String url;
	
	private boolean visible;
	
	private List<AccordionBean> menus;

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<AccordionBean> getMenus() {
		return menus;
	}

	public void setMenus(List<AccordionBean> menus) {
		this.menus = menus;
	}
}
