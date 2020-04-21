package com.wise.develop.ChatApis.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/chat/{userId}")
public class ChatService {

    private static final ConcurrentHashMap<String, ChatService> webSocketSet = new ConcurrentHashMap<>();

    private Session session;
    private String userId;

    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId, Session session) {
        this.userId = userId;
        this.session = session;

        webSocketSet.put(userId, this);

        System.out.println("当前连接的用户是" + userId + "--- 当前session是" +
                session.hashCode() + "在线人数：" + webSocketSet.size());
    }

    public void sendToUser(String toUserId, String message) {
        try {
            if (webSocketSet.get(toUserId) != null) {
                webSocketSet.get(toUserId).sendMessage(message);
            }
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}