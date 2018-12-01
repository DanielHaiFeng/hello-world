package com.xa.dz.ims.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xa.dz.ims.mapper.AuthorizeMapper;
import com.xa.dz.ims.mapper.MenuMapper;
import com.xa.dz.ims.model.Authorize;
import com.xa.dz.ims.model.AuthorizeExample;
import com.xa.dz.ims.model.Menu;
import com.xa.dz.ims.service.AuthorizeService;
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

import java.util.Arrays;
import java.util.List;

@Service("authorizeService")
public class AuthorizeServiceImpl implements AuthorizeService {

    private final static Logger logger = LoggerFactory.getLogger(AuthorizeServiceImpl.class);

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    AuthorizeMapper authorizeMapper;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    DefaultTransactionDefinition defaultTransactionDefinition;

    @Override
    public JSONArray getAuthorizeMenuTree(Integer uid) {
        logger.info("获取用户id集合为[{}]为父节点的树结构",uid);
        JSONArray array = new JSONArray();
        try{
            List<Menu> mblist = menuMapper.getAuthorizeMenus(uid);
            JSONObject mt = new JSONObject();
            mt.put("id", 0);
            mt.put("text", "菜单");
            mt.put("state", "open");
            mt.put("iconCls", "icon-ok");
            mt.put("checked", false);
            JSONObject ta = new JSONObject();
            ta.put("mlevel", 0);
            mt.put("attributes", ta);
            this.assembleAuthorizeMenuTree(mt, mblist);
            array.add(mt);
        }catch (Exception e){
            logger.error("获取菜单树异常:", e);
        }
        return array;
    }

    @Override
    public JSONObject authorizeMenu(int uid, String mids) {
        JSONObject or = new JSONObject();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        try{
            AuthorizeExample example = new AuthorizeExample();
            example.createCriteria().andUidEqualTo(uid);
            authorizeMapper.deleteByExample(example);
            List<String> midlist = Arrays.asList(mids.split(","));
            for(String mid:midlist){
                Authorize authorize = new Authorize();
                authorize.setMid(Integer.parseInt(mid));
                authorize.setUid(uid);
                authorizeMapper.insert(authorize);
            }
            transactionManager.commit(status);
            or.put("result", 0);
            or.put("msg", "授权成功！");
        }catch (Exception e) {
            transactionManager.rollback(status);
            logger.error("授权异常:", e);
            or.put("result", 2);
            or.put("msg", "授权失败，请联系管理员！");
        }
        return or;
    }

    public JSONObject assembleAuthorizeMenuTree(JSONObject mt, List<Menu> mblist) {
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
                    String checked = ObjectUtils.toString(mb.getChecked());
                    mtt.put("checked",StringUtils.isBlank(checked)?false:Boolean.valueOf(checked));
                    mtt.put("iconCls", mb.getMicon());
                    JSONObject ta = new JSONObject();
                    ta.put("mlevel", mb.getMlevel());
                    mtt.put("attributes", ta);
                    mblist.remove(i);
                    assembleAuthorizeMenuTree(mtt, mblist);
                    mtlist.add(mtt);
                }
            }
        }
        return mt;
    }
}
