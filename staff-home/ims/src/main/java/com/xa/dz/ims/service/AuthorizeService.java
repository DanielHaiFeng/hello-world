package com.xa.dz.ims.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface AuthorizeService {

    public JSONArray getAuthorizeMenuTree(Integer uid);

    public JSONObject authorizeMenu(int uid, String mids);
}
