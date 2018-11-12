package com.xa.dz.ims.service;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

public interface MenuService {

    public JSONArray getMenuTree(Integer pid);

    Map<String, Object> pageMenu(int pageNum, int pageSize, int mid);
}
