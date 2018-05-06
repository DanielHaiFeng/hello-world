package com.xa.dt.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.dt.springboot.bean.ComboBox;
import com.xa.dt.springboot.bean.User;
import com.xa.dt.springboot.dao.BaseDao;
import com.xa.dt.springboot.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService {
	
	Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	@Autowired
	BaseDao baseDao;

	@Override
	public List<ComboBox> getTableComboBox(String type) {
		
		log.info("请求的type是[{}]", type);
		
		List<ComboBox> list = new ArrayList<ComboBox>();
		ComboBox c1 = new ComboBox();
		c1.setId("101");
		c1.setText("陕西");
		
		ComboBox c2 = new ComboBox();
		c2.setId("102");
		c2.setText("山西");
		
		ComboBox c3 = new ComboBox();
		c3.setId("103");
		c3.setText("山东");
		
		ComboBox c4 = new ComboBox();
		c4.setId("104");
		c4.setText("河北");
		
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		
		return list;
	}

	@Override
	public List<ComboBox> getUser() {
		List<User> list = baseDao.queryUsers();
		List<ComboBox> cbl = new ArrayList<ComboBox>();
		for(User u:list){
			ComboBox cb = new ComboBox();
			cb.setId(String.valueOf(u.getId()));
			cb.setText(u.getName());
			cbl.add(cb);
		}
		return cbl;
	}

}
