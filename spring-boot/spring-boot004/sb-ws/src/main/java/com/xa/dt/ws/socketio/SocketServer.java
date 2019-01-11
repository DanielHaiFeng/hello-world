package com.xa.dt.ws.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketServer {

    static Logger logger = LoggerFactory.getLogger(SocketServer.class);

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        SocketIOServer server = new SocketIOServer(config);

        CharteventListener listner = new CharteventListener();
        listner.setServer(server);
        //chatevent为事件名称
        server.addEventListener("chatevent", ChatObject.class, listner);
        logger.info("注册chatevent事件监听成功");
        //启动服务
        server.start();
        logger.info("netty-socket.io服务端启动成功");

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }
}