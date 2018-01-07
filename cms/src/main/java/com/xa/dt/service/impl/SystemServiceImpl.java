package com.xa.dt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.dt.beans.AccordionBean;
import com.xa.dt.beans.DataGrid;
import com.xa.dt.beans.MenuBean;
import com.xa.dt.beans.MenuTree;
import com.xa.dt.beans.OpResult;
import com.xa.dt.beans.TreeAttributes;
import com.xa.dt.beans.UserBean;
import com.xa.dt.dao.MenuDao;
import com.xa.dt.dao.UserDao;
import com.xa.dt.service.ISystemService;

import net.sf.json.JSONArray;

@Service("systemService")
public class SystemServiceImpl implements ISystemService {
	
	private static Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

	@Autowired
	MenuDao menuDao;

	@Autowired
	UserDao userDao;

	@Override
	public OpResult login(String uname, String upwd) {
		// TODO Auto-generated method stub
		int result = userDao.isLogin(uname, upwd);
		OpResult or = new OpResult();
		if (result == 1) {
			or.setResult(true);
			or.setMsg("登录成功！");
		} else {
			or.setResult(false);
			or.setMsg("用户名或密码不正确！");
		}
		return or;
	}

	@Override
	public List<AccordionBean> getAccordion() {
		List<MenuBean> mblist = menuDao.getMenus(null);
		AccordionBean ab = new AccordionBean();
		ab.setMenuid(0);
		ab.setIcon("icon-ok");
		ab.setMenuname("root");
		ab.setUrl("root");
		this.assembleAccordion(ab, mblist);
		logger.info("获取到的手风琴原始json数据为：{}",JSONArray.fromObject(ab.getMenus()).toString());
		return ab.getMenus();
	}

	@Override
	public boolean insertMenu(MenuBean menu) {
		int ri = menuDao.insert(menu);
		return ri == 1 ? true : false;
	}

	@Override
	public List<MenuTree> getMenuTree(String id) {
		List<MenuTree> list = new ArrayList<MenuTree>();
		List<MenuBean> mblist = menuDao.getMenus(StringUtils.isNotBlank(id) ? Integer.parseInt(id) : null);
		MenuTree mt = new MenuTree();
		mt.setId("0");
		mt.setText("菜单");
		mt.setState("open");
		mt.setIconCls("icon-ok");
		TreeAttributes ta = new TreeAttributes();
		ta.setMlevel(0);
		mt.setAttributes(ta);
		this.assembleMenuTree(mt, mblist);
		list.add(mt);
		return list;
	}
	
	@Override
	public DataGrid<MenuBean> getMenuList(int page, int rows, int mid) {
		DataGrid<MenuBean> dg = new DataGrid<MenuBean>();
		MenuBean mb = new MenuBean();
		mb.setPid(mid);
		mb.setPageInfo(page, rows);
		List<MenuBean> ulist = menuDao.getMenuList(mb);
		dg.setTotal(menuDao.getMenuCount(mb));
		dg.setRows(ulist);
		return dg;
	}

	@Override
	public OpResult insertUser(UserBean user) {
		OpResult or = new OpResult();
		String uname = user.getUname();
		boolean isExist = userDao.isUserExist(uname) == 1 ? true :false ;
		if(isExist){
			or.setResult(false);
			or.setMsg("用户["+uname+"]已存在，请更换用户名！");
		}else{
			int ac = userDao.insert(user);
			or.setResult(ac == 1 ? true : false);
			if(or.isResult()){
				or.setMsg("添加成功");
			}else {
				or.setMsg("添加失败");
			}
		}
		return or;
	}

	@Override
	public OpResult updateUser(UserBean user) {
		OpResult or = new OpResult();
		String uname = user.getUname();
		boolean isExist = userDao.isUserExistForEdit(uname, user.getUid()) == 1 ? true :false ;
		if(isExist){
			or.setResult(false);
			or.setMsg("用户["+uname+"]已存在，请更换用户名！");
		}else{
			int ac = userDao.updateByPrimaryKey(user);
			or.setResult(ac == 1 ? true : false);
			if(or.isResult()){
				or.setMsg("编辑成功");
			}else {
				or.setMsg("编辑失败");
			}
		}
		return or;
	}

	@Override
	public OpResult deleteUsers(List<Integer> uid) {
		OpResult or = new OpResult();
		int bdc = userDao.deleteByIds(uid);
		or.setResult(bdc == uid.size() ? true : false);
		if(or.isResult()){
			or.setMsg("删除成功");
		}else {
			or.setMsg("删除失败");
		}
		return or;
	}

	@Override
	public UserBean getUser(int uid) {
		return userDao.selectByPrimaryKey(uid);
	}

	@Override
	public DataGrid<UserBean> getUsers(int page, int rows, UserBean ub) {
		DataGrid<UserBean> dg = new DataGrid<UserBean>();
		if (ub == null) {
			ub = new UserBean();
		}
		ub.setPageInfo(page, rows);
		List<UserBean> ulist = userDao.getUsers(ub);
		dg.setTotal(userDao.getUserCount(ub));
		dg.setRows(ulist);
		return dg;
	}
	
	public MenuTree assembleMenuTree(MenuTree mt, List<MenuBean> mblist) {
		if (mblist.size() != 0) {
			if (mt.getChildren() == null) {
				mt.setChildren(new ArrayList<MenuTree>());
			}
			int id = Integer.parseInt(mt.getId());
			List<MenuTree> mtlist = mt.getChildren();
			for(int i=mblist.size()-1;i>=0;i--){
				MenuBean mb = mblist.get(i);
				if (mb.getPid() == id) {
					MenuTree mtt = new MenuTree();
					mtt.setId(String.valueOf(mb.getMid()));
					mtt.setText(mb.getMname());
					mtt.setState("close");
					mtt.setIconCls(mb.getMicon());
					TreeAttributes ta = new TreeAttributes();
					ta.setMlevel(mb.getMlevel());
					mtt.setAttributes(ta);
					mblist.remove(i);
					assembleMenuTree(mtt, mblist);
					mtlist.add(mtt);
				}
			}
		}
		return mt;
	}
	
	public AccordionBean assembleAccordion(AccordionBean ab, List<MenuBean> mblist) {
		if (mblist.size() != 0) {
			if (ab.getMenus() == null) {
				ab.setMenus(new ArrayList<AccordionBean>());
			}
			int id = ab.getMenuid();
			List<AccordionBean> ablist = ab.getMenus();
			for(int i=mblist.size()-1;i>=0;i--){
				MenuBean mb = mblist.get(i);
				if (mb.getPid() == id) {
					AccordionBean abt = new AccordionBean();
					abt.setMenuid(mb.getMid());
					abt.setMenuname(mb.getMname());
					abt.setIcon(mb.getMicon());
					abt.setUrl(mb.getUrl());
					mblist.remove(i);
					assembleAccordion(abt, mblist);
					ablist.add(abt);
				}
			}
		}
		return ab;
	}
}
