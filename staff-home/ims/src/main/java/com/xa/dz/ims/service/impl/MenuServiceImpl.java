package com.xa.dz.ims.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xa.dz.ims.mapper.AuthorizeMapper;
import com.xa.dz.ims.mapper.MenuMapper;
import com.xa.dz.ims.mapper.UserMapper;
import com.xa.dz.ims.model.*;
import com.xa.dz.ims.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private final static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    AuthorizeMapper authorizeMapper;

    @Autowired
    PageHelper pageHelper;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    DefaultTransactionDefinition transactionDefinition;

    @Override
    public JSONArray getMenuTree(Integer pid) {
        logger.debug("获取以节点[{}]为父节点的树结构",pid);
        JSONArray list = new JSONArray();
        try{
            MenuExample menuExample = new MenuExample();
            if(null != pid){
                menuExample.createCriteria().andPidEqualTo(pid);
            }
            List<Menu> mblist = menuMapper.selectByExample(menuExample);
            JSONObject mt = new JSONObject();
            mt.put("id", 0);
            mt.put("text", "菜单");
            mt.put("state", "open");
            mt.put("iconCls", "icon-ok");
            JSONObject ta = new JSONObject();
            ta.put("mlevel", 0);
            mt.put("attributes", ta);
            this.assembleMenuTree(mt, mblist);
            list.add(mt);
        }catch (Exception e) {
            logger.error("获取菜单树异常:", e);
        }
        return list;
    }

    @Override
    public Map<String, Object> pageMenu(int pageNum, int pageSize, int mid) {
        logger.debug("菜单列表分页 第[{}]页 每页[{}]行", pageNum, pageSize);
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andPidEqualTo(mid);
        Page page = pageHelper.startPage(pageNum, pageSize, true);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", menuMapper.selectByExample(menuExample));
        map.put("total", page.getTotal());
        return map;
    }

    @Override
    public JSONObject createMenu(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try{
            Menu menu = initCreateMenu(request);
            MenuExample menuExample = new MenuExample();
            menuExample.createCriteria().andMnameEqualTo(menu.getMname());
            if (menuMapper.selectByExample(menuExample).size() > 0) {
                object.put("success", false);
                object.put("message", "菜单名已占用");
                transactionManager.rollback(status);
            } else {
                int result = menuMapper.insert(menu);
                if (result == 1) {
                    JSONObject mt = new JSONObject();
                    Menu mb = menuMapper.selectByExample(menuExample).get(0);
                    mt.put("id", mb.getMid());
                    mt.put("text", mb.getMname());
                    mt.put("state", "close");
                    mt.put("iconCls", mb.getMicon());
                    JSONObject ta = new JSONObject();
                    ta.put("mlevel", mb.getMlevel());
                    mt.put("attributes", ta);
                    mt.put("menuObj", mt);

                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andLoginnameEqualTo("super");
                    User ub = userMapper.selectByExample(userExample).get(0);
                    Authorize ab = new Authorize();
                    ab.setUid(ub.getUid());
                    ab.setMid(mb.getMid());
                    authorizeMapper.insert(ab);
                    transactionManager.commit(status);

                    object.put("success", true);
                    object.put("message", "新增成功");
                    object.put("menuObj", mt);
                } else {
                    object.put("success", false);
                    object.put("message", "新增失败");
                    transactionManager.rollback(status);
                }
            }
        }catch (Exception e) {
            transactionManager.rollback(status);
            logger.error("新增菜单异常:", e);
            object.put("success", false);
            object.put("message", "新增菜单异常:" + e.getMessage());
        }
        return object;
    }

    @Override
    public JSONObject deleteMenu(List<Integer> mids) {
        JSONObject object = new JSONObject();
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andMidIn(mids);
        try {
            AuthorizeExample authorizeExample = new AuthorizeExample();
            for (int mid : mids) {
                authorizeExample.clear();
                authorizeExample.createCriteria().andMidEqualTo(mid);
                authorizeMapper.deleteByExample(authorizeExample);
            }
            int result = menuMapper.deleteByExample(menuExample);
            if (result == mids.size()) {
                object.put("success", true);
                object.put("message", "删除成功");
                transactionManager.commit(status);
            } else {
                object.put("success", false);
                object.put("message", "删除失败");
                transactionManager.rollback(status);
            }
        }catch (Exception e) {
            transactionManager.rollback(status);
            logger.error("删除菜单异常:", e);
            object.put("success", false);
            object.put("message", "删除菜单异常:" + e.getMessage());
        }
        return object;
    }

    public JSONObject assembleMenuTree(JSONObject mt, List<Menu> mblist) {
        if (mblist.size() != 0) {
            if (mt.get("children") == null) {
                mt.put("children", new JSONArray());
            }
            int id = mt.getInteger("id");
            JSONArray mtlist = mt.getJSONArray("children");
            for(int i=mblist.size()-1;i>=0;i--){
                Menu mb = mblist.get(i);
                if (mb.getPid() == id) {
                    JSONObject mtt = new JSONObject();
                    mtt.put("id", mb.getMid());
                    mtt.put("text", mb.getMname());
                    mtt.put("state", "close");
                    mtt.put("iconCls", mb.getMicon());
                    JSONObject ta = new JSONObject();
                    ta.put("mlevel", mb.getMlevel());
                    mtt.put("attributes", ta);
                    mblist.remove(i);
                    assembleMenuTree(mtt, mblist);
                    mtlist.add(mtt);
                }
            }
        }
        return mt;
    }

    private Menu initCreateMenu(HttpServletRequest request) {
        String pid = request.getParameter("pid");
        String mname = request.getParameter("mname");
        String mlevel = request.getParameter("mlevel");
        String url = request.getParameter("url");
        String micon = "icon-ok";
        Menu menu = new Menu();
        menu.setMicon(micon);
        menu.setPid(Integer.parseInt(pid));
        menu.setMlevel(Integer.parseInt(mlevel));
        menu.setMname(mname);
        menu.setUrl(url);
        return menu;
    }
}
