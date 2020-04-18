package com.wiseapis.chat.service;

import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
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
            } else {
                System.out.println("当前用户不在线");
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException, EncodeException {
        this.session.getBasicRemote().sendText(message);
    }
}