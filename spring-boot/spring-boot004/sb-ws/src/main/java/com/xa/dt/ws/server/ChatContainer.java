package com.xa.dt.ws.server;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChatContainer {

    private static ChatContainer chatContainer;

    public static ChatContainer getInstance() {
        if (chatContainer == null) {
            chatContainer = new ChatContainer();
        }
        return chatContainer;
    }

    private int onlineCount = 0;

    private CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    private int getOnlineCount() {
        return onlineCount;
    }

    private void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public synchronized int getCount() {
        return getOnlineCount();
    }

    private void addOnline() {
        setOnlineCount(getOnlineCount() + 1);
    }

    private void subOnline() {
        setOnlineCount(getOnlineCount() - 1);
    }

    public synchronized void addChater(WebSocketServer server) {
        webSocketSet.add(server);
        addOnline();
    }

    public synchronized void removeChater(WebSocketServer server) {
        webSocketSet.remove(server);
        subOnline();
    }

    public synchronized WebSocketServer getChater(String chatId) {
        for (WebSocketServer ws : webSocketSet) {
            if (ws.getChatId().equals(chatId)) {
                return ws;
            }
        }
        return null;
    }

    public synchronized WebSocketServer getChater(Session session) {
        for (WebSocketServer ws : webSocketSet) {
            if (ws.getSession() == session) {
                return ws;
            }
        }
        return null;
    }

    public synchronized List<WebSocketServer> getOtherChater(Session session) {
        List<WebSocketServer> webSocketServers = new ArrayList<WebSocketServer>();
        for (WebSocketServer ws : webSocketSet) {
            if (ws.getSession() != session) {
                webSocketServers.add(ws);
            }
        }
        return webSocketServers;
    }

    public CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}
