package com.xa.dt.ws.controller;

import com.alibaba.fastjson.JSONObject;
import com.xa.dt.ws.server.ChatContainer;
import com.xa.dt.ws.server.WebSocketServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Author DangTing[dangting@boco.com.cn]
 * @CreateDate 2018/12/28 10:35
 * @Description
 * @Copyright Copyright 2018/12/28 10:35 BOCO. All rights reserved
 */
@Controller
public class PageController {

    Logger logger = LoggerFactory.getLogger(PageController.class);

    ChatContainer chatContainer;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        logger.info("跳转到登录页面");
        return "login";
    }

    @RequestMapping(value = "/getChaters", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getChaters(@RequestParam("slfChatId") String slfChatId) {
        chatContainer = ChatContainer.getInstance();
        Set<WebSocketServer> wss = chatContainer.getWebSocketSet();
        logger.info("获取客户端[{}]之外的其他客户端", slfChatId);
        List<JSONObject> wsList = new ArrayList<JSONObject>();
        for (WebSocketServer ws : wss) {
            if (StringUtils.isNoneBlank(ws.getChatId()) && !ws.getChatId().equals(slfChatId)) {
                JSONObject obj = new JSONObject();
                obj.put("chatId", ws.getChatId());
                wsList.add(obj);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rows", wsList);
        map.put("total", wsList.size());
        return map;
    }
}
