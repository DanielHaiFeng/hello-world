package com.xa.dz.ims.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xa.dz.ims.mapper.UserMapper;
import com.xa.dz.ims.model.User;
import com.xa.dz.ims.model.UserExample;
import com.xa.dz.ims.service.UserService;
import com.xa.dz.ims.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map<String, Object> pageUser(int pageNum, int pageSize) {
        logger.debug("用户列表分页");
        Page page = pageHelper.startPage(pageNum, pageSize, true);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", userMapper.selectAllUser());
        map.put("total", page.getTotal());
        return map;
    }
}
