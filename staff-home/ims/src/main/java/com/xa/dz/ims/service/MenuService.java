package com.xa.dz.ims.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface MenuService {

    public JSONArray getMenuTree(Integer pid);

    Map<String, Object> pageMenu(int pageNum, int pageSize, int mid);

    public JSONObject createMenu(HttpServletRequest request);

    public JSONObject deleteMenu(List<Integer> mids);
}
