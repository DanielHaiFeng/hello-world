package com.xa.dz.ims.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xa.dz.ims.mapper.UserMapper;
import com.xa.dz.ims.model.User;
import com.xa.dz.ims.model.UserExample;
import com.xa.dz.ims.service.UserService;
import com.xa.dz.ims.utils.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author DangTing[dangting@boco.com.cn]
 * @CreateDate 2018/11/9 9:04
 * @Description
 * @Copyright Copyright 2018/11/9 9:04 BOCO. All rights reserved
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    PageHelper pageHelper;

    @Autowired
    Base64 base64;

    @Override
    public JSONObject login(String userName, String password) {
        JSONObject object = new JSONObject();
        try {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andLoginnameEqualTo(userName);
            List<User> users = userMapper.selectByExample(userExample);
            if (users.size() == 0) {
                object.put("success", false);
                object.put("message", "用户名错误");
                logger.debug("用户[{}]登录失败，原因[用户名错误]", userName);
            } else {
                User user = users.get(0);
                String pwdTmp = base64.getFromBase64(user.getUpwd());
                if (password.equals(pwdTmp)) {
                    object.put("success", true);
                } else {
                    object.put("success", false);
                    object.put("message", "密码错误");
                    logger.debug("用户[{}]登录失败，原因[密码错误]", userName);
                }
            }
        } catch (Exception e) {
            logger.error("登录异常:", e);
            object.put("success", false);
            object.put("message", "登录异常:" + e.getMessage());
        }
        return object;
    }

    @Override
    public Map<String, Object> pageUser(int pageNum, int pageSize, User user) {
        logger.debug("用户列表分页 第[{}]页 每页[{}]行", pageNum, pageSize);
        UserExample userExample = new UserExample();
        initPageUserExample(userExample, user);
        Page page = pageHelper.startPage(pageNum, pageSize, true);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", userMapper.selectByExample(userExample));
        map.put("total", page.getTotal());
        return map;
    }

    @Override
    public JSONObject createUser(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        try{
            User user = initCreateUser(request);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andLoginnameEqualTo(user.getLoginname());
            if (userMapper.selectByExample(userExample).size() > 0) {
                object.put("success", false);
                object.put("message", "登录名已占用");
            } else {
                int result = userMapper.insert(user);
                if (result == 1) {
                    object.put("success", true);
                    object.put("message", "添加成功");
                } else {
                    object.put("success", false);
                    object.put("message", "添加失败");
                }
            }
        }catch (Exception e) {
            logger.error("创建用户异常:", e);
            object.put("success", false);
            object.put("message", "创建用户异常" + e.getMessage());
        }
        return object;
    }

    @Override
    public JSONObject editUser(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        try{
            User user = initEditUser(request);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUidNotEqualTo(user.getUid()).andLoginnameEqualTo(user.getLoginname());
            if (userMapper.selectByExample(userExample).size() > 0) {
                object.put("success", false);
                object.put("message", "登录名已占用");
            } else {
                int result = userMapper.updateByPrimaryKeySelective(user);
                if (result == 1) {
                    object.put("success", true);
                    object.put("message", "编辑成功");
                } else {
                    object.put("success", false);
                    object.put("message", "编辑失败");
                }
            }
        }catch (Exception e) {
            logger.error("编辑用户异常:", e);
            object.put("success", false);
            object.put("message", "编辑用户异常" + e.getMessage());
        }
        return object;
    }

    @Override
    public JSONObject deleteUser(List<Integer> uids) {
        JSONObject object = new JSONObject();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUidIn(uids);
        try {
            int result = userMapper.deleteByExample(userExample);
            if (result == uids.size()) {
                object.put("success", true);
                object.put("message", "删除成功");
            } else {
                object.put("success", false);
                object.put("message", "删除失败");
            }
        }catch (Exception e) {
            logger.error("删除用户异常:", e);
            object.put("success", false);
            object.put("message", "删除用户异常" + e.getMessage());
        }
        return object;
    }

    private void initPageUserExample(UserExample userExample, User user) {
        if (null == user) {
            return;
        }
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(user.getLoginname())) {
            criteria.andLoginnameLike(user.getLoginname());
        }
        if (StringUtils.isNotBlank(user.getName())) {
            criteria.andNameLike(user.getName());
        }
        if (StringUtils.isNotBlank(user.getCellphone())) {
            criteria.andCellphoneLike(user.getCellphone());
        }
        if (StringUtils.isNotBlank(user.getAddress())) {
            criteria.andAddressLike(user.getAddress());
        }
        if (StringUtils.isNotBlank(user.getRemark())) {
            criteria.andRemarkLike(user.getRemark());
        }
    }

    private User initCreateUser(HttpServletRequest request) {
        String loginname = request.getParameter("loginname");
        String name = request.getParameter("name");
        String cellphone = request.getParameter("cellphone");
        String address = request.getParameter("address");
        String remark = request.getParameter("remark");
        User user = new User();
        user.setLoginname(loginname);
        user.setName(name);
        user.setCellphone(cellphone);
        user.setAddress(address);
        user.setUpwd(base64.getBase64("123456"));
        user.setRemark(remark);
        return user;
    }

    private User initEditUser(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        String loginname = request.getParameter("loginname");
        String name = request.getParameter("name");
        String cellphone = request.getParameter("cellphone");
        String address = request.getParameter("address");
        String remark = request.getParameter("remark");
        User user = new User();
        user.setUid(Integer.parseInt(uid));
        user.setLoginname(loginname);
        user.setName(name);
        user.setCellphone(cellphone);
        user.setAddress(address);
        user.setRemark(remark);
        return user;
    }
}
