package com.xa.dt.springboot.service;

import java.util.List;
import com.xa.dt.springboot.bean.ComboBox;


public interface BaseService {

	public List<ComboBox> getTableComboBox(String type);
	
	public List<ComboBox> getUser();
}
