package com.xa.dt.ws.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {

    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    ChatContainer chatContainer = ChatContainer.getInstance();

    private Session session;

    private String chatId;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        logger.info("类标识[{}]", this.toString());
        this.session = session;
        chatContainer.addChater(this);
        logger.info("有新连接加入！当前在线人数为" + chatContainer.getCount());
        sendMessage("success");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        chatContainer.removeChater(this);
        logger.info("有一连接关闭！当前在线人数为" + chatContainer.getCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息:" + message);
        if (StringUtils.isNotBlank(message)) {
            if (message.startsWith("#REG#")) {
                //某个客户端连接，格式#REG#chatId
                String tmpChatId = message.substring(5, message.length());
                WebSocketServer ws = chatContainer.getChater(session);
                ws.setChatId(tmpChatId);
                noticeNewComerToOthers(session);
            } else if (message.startsWith("#PC#")) {
                //跟某个客户端私聊，格式#PC#chatId#message
                int lastChar = message.indexOf("#", 4);
                String tmpChatId = message.substring(4, lastChar);
                String realMessage = message.substring(lastChar + 1, message.length());
                WebSocketServer ws = chatContainer.getChater(tmpChatId);
                if (null != ws) {
                    ws.sendMessage("#FRM#" + this.getChatId() + "#" + realMessage);
                } else {
                    throw new RuntimeException("客户端" + tmpChatId + "没有连接至服务器");
                }
            } else {
                throw new RuntimeException("暂时只支持私聊");
            }
        }
    }

    private void noticeNewComerToOthers(Session session) {
        List<WebSocketServer> lsit = chatContainer.getOtherChater(session);
        if (lsit.size() != 0) {
            for (WebSocketServer webSocketServer : lsit) {
                webSocketServer.sendMessage("newComer");
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        try {
            this.getSession().getBasicRemote().sendText(message);
        } catch (Exception e) {
            logger.error("发送消息异常:", e);
            throw new RuntimeException("发送消息异常:" + e.getMessage());
        }
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
