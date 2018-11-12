package com.xa.dz.ims.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xa.dz.ims.mapper.MenuMapper;
import com.xa.dz.ims.model.Menu;
import com.xa.dz.ims.model.MenuExample;
import com.xa.dz.ims.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private final static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    PageHelper pageHelper;

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
}
