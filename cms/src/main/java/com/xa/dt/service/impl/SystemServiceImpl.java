package com.xa.dt.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.xa.dt.beans.AccordionBean;
import com.xa.dt.beans.AuthorizeBean;
import com.xa.dt.beans.DataGrid;
import com.xa.dt.beans.MenuBean;
import com.xa.dt.beans.MenuTree;
import com.xa.dt.beans.OpResult;
import com.xa.dt.beans.TreeAttributes;
import com.xa.dt.beans.UserBean;
import com.xa.dt.dao.AuthorizeDao;
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
	
	@Autowired
	AuthorizeDao authorizeDao;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Autowired
	DefaultTransactionDefinition defaultTransactionDefinition;

	@Override
	public OpResult login(String uname, String upwd) {
		// TODO Auto-generated method stub
		int result = userDao.isLogin(uname, upwd);
		OpResult or = new OpResult();
		if (result == 1) {
			or.setResult(0);
			or.setMsg("登录成功！");
		} else {
			or.setResult(2);
			or.setMsg("用户名或密码不正确！");
		}
		return or;
	}

	@Override
	public List<AccordionBean> getAccordion(String uname) {
		UserBean ub = userDao.getUserByName(uname);
		List<MenuBean> mblist = menuDao.getAuthorizeMenus(ub.getUid());
		AccordionBean ab = new AccordionBean();
		ab.setMenuid(0);
		ab.setIcon("icon-ok");
		ab.setMenuname("root");
		ab.setUrl("root");
		ab.setVisible(true);
		this.assembleAccordion(ab, mblist);
		logger.info("获取到的手风琴原始json数据为：{}",JSONArray.fromObject(ab.getMenus()).toString());
		return ab.getMenus();
	}

	@Override
	public OpResult insertMenu(MenuBean menu) {
		OpResult or = new OpResult();
		String mname = menu.getMname();
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
		try {
			boolean isExist = menuDao.isMenuExist(mname) == 1 ? true : false;
			if (isExist) {
				or.setResult(1);
				or.setMsg("菜单[" + mname + "]已存在，请更换菜单名！");
			} else {
				int ac = menuDao.insert(menu);
				or.setResult(ac == 1 ? 0 : 2);
				if (or.getResult()==0) {
					or.setMsg("添加成功");
					MenuTree mt = new MenuTree();
					MenuBean mb = menuDao.getMenuByName(menu.getMname());
					mt.setId(String.valueOf(mb.getMid()));
					mt.setText(mb.getMname());
					mt.setState("close");
					mt.setIconCls(mb.getMicon());
					TreeAttributes ta = new TreeAttributes();
					ta.setMlevel(mb.getMlevel());
					mt.setAttributes(ta);
					or.setObj(mt);
					UserBean ub = userDao.getUserByName("super");
					AuthorizeBean ab = new AuthorizeBean();
					ab.setUid(ub.getUid());
					ab.setMid(mb.getMid());
					authorizeDao.insert(ab);
					transactionManager.commit(status);
				} else {
					or.setMsg("添加失败");
				}
			}
		} catch (Exception e) {
			logger.error("菜单保存失败！", e);
			or.setMsg("添加失败");
			or.setResult(2);
			transactionManager.rollback(status);
		}
		return or;
	}

	@Override
	public OpResult deleteMenu(List<Integer> mids) {
		OpResult or = new OpResult();
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
		try{
			for(int mid:mids){
				authorizeDao.deleteAuthorizeByMid(mid);
			}
			int bdc = menuDao.deleteByIds(mids);
			or.setResult(bdc == mids.size() ? 0 : 2);
			if(or.getResult()==0){
				or.setMsg("删除成功");
				transactionManager.commit(status);
			}else {
				or.setMsg("删除失败");
			}
		}catch (Exception e) {
			logger.error("菜单删除失败！", e);
			or.setMsg("删除失败");
			or.setResult(2);
			transactionManager.rollback(status);
		}
		return or;
	}
	
	@Override
	public MenuBean getMenu(int mid) {
		return menuDao.selectByPrimaryKey(mid);
	}

	@Override
	public OpResult updateMenu(MenuBean mb) {
		OpResult or = new OpResult();
		String mname = mb.getMname();
		boolean isExist = menuDao.isMenuExistForEdit(mname, mb.getMid()) == 1 ? true :false ;
		if(isExist){
			or.setResult(1);
			or.setMsg("菜单["+mname+"]已存在，请更换菜单名！");
		}else{
			int ac = menuDao.updateByPrimaryKeySelective(mb);
			or.setResult(ac == 1 ? 0 : 2);
			if(or.getResult()==0){
				or.setMsg("编辑成功");
			}else {
				or.setMsg("编辑失败");
			}
		}
		return or;
	}

	@Override
	public List<MenuTree> getMenuTree(String pid) {
		logger.info("获取以节点[{}]为父节点的树结构",pid);
		List<MenuTree> list = new ArrayList<MenuTree>();
		List<MenuBean> mblist = menuDao.getMenus(StringUtils.isNotBlank(pid) ? Integer.parseInt(pid) : null);
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
	public List<MenuTree> getAuthorizeMenuTree(Integer uid) {
		logger.info("获取用户id集合为[{}]为父节点的树结构",uid);
		List<MenuTree> list = new ArrayList<MenuTree>();
		List<MenuBean> mblist = menuDao.getAuthorizeMenus(uid);
		MenuTree mt = new MenuTree();
		mt.setId("0");
		mt.setText("菜单");
		mt.setState("open");
		mt.setIconCls("icon-ok");
		mt.setChecked(false);
		TreeAttributes ta = new TreeAttributes();
		ta.setMlevel(0);
		mt.setAttributes(ta);
		this.assembleAuthorizeMenuTree(mt, mblist);
		list.add(mt);
		return list;
	}
	
	@Override
	public OpResult authorizeMenu(int uid, String mids) {
		OpResult or = new OpResult();
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
		try{
			authorizeDao.deleteAuthorize(uid);
			List<String> midlist = Arrays.asList(mids.split(","));
			for(String mid:midlist){
				AuthorizeBean ab = new AuthorizeBean();
				ab.setMid(Integer.parseInt(mid));
				ab.setUid(uid);
				authorizeDao.insert(ab);
			}
			transactionManager.commit(status);
			or.setResult(0);
			or.setMsg("授权成功！");
		}catch (Exception e) {
			transactionManager.rollback(status);
			logger.error(e.getMessage(), e);
			or.setResult(2);
			or.setMsg("授权失败，请联系管理员！");
		}
		return or;
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
			or.setResult(1);
			or.setMsg("用户["+uname+"]已存在，请更换用户名！");
		}else{
			int ac = userDao.insert(user);
			or.setResult(ac == 1 ? 0 : 2);
			if(or.getResult()==0){
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
			or.setResult(1);
			or.setMsg("用户["+uname+"]已存在，请更换用户名！");
		}else{
			int ac = userDao.updateByPrimaryKeySelective(user);
			or.setResult(ac == 1 ? 0 : 2);
			if(or.getResult()==0){
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
		or.setResult(bdc == uid.size() ? 0 : 2);
		if(or.getResult()==0){
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
	
	public MenuTree assembleAuthorizeMenuTree(MenuTree mt, List<MenuBean> mblist) {
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
					String checked = ObjectUtils.toString(mb.getChecked());
					mtt.setChecked(StringUtils.isBlank(checked)?false:Boolean.valueOf(checked));
					mtt.setIconCls(mb.getMicon());
					TreeAttributes ta = new TreeAttributes();
					ta.setMlevel(mb.getMlevel());
					mtt.setAttributes(ta);
					mblist.remove(i);
					assembleAuthorizeMenuTree(mtt, mblist);
					mtlist.add(mtt);
				}
			}
		}
		return mt;
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
					abt.setVisible(Boolean.valueOf(mb.getChecked()));
					mblist.remove(i);
					assembleAccordion(abt, mblist);
					ablist.add(abt);
				}
			}
		}
		return ab;
	}
}
