package com.xa.dt.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xa.dt.springboot.bean.ComboBox;
import com.xa.dt.springboot.service.BaseService;

@Controller
public class BaseController {

	Logger log = LoggerFactory.getLogger(BaseController.class);
	
	@Resource(name = "baseService")
	BaseService baseService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		log.info("跳转到web项目首页！");
		return "index";
	}
	
	@RequestMapping("/getComboBox")
	@ResponseBody
	public List<ComboBox> getComboBox(@RequestParam String type) {
		return baseService.getTableComboBox(type);
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public List<ComboBox> getUser() {
		return baseService.getUser();
	}
}