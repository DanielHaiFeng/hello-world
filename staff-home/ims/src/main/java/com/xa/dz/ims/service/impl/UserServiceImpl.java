package com.xa.dz.ims.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xa.dz.ims.mapper.UserMapper;
import com.xa.dz.ims.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
