package com.xa.dt.ws.socketio;

import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharteventListener implements DataListener<ChatObject> {

    Logger logger = LoggerFactory.getLogger(CharteventListener.class);

    SocketIOServer server;

    public void setServer(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void onData(SocketIOClient client, ChatObject data, AckRequest ackSender) throws Exception {
        //chatevent为 事件的名称，data为发送的内容
        logger.info("收到chatevent的数据[{}]", JSONObject.toJSONString(data));
        this.server.getBroadcastOperations().sendEvent("chatevent", data);
    }
}
