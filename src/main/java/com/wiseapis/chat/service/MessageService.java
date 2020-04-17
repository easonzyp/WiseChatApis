package com.wiseapis.chat.service;

import com.wiseapis.chat.bean.MessageBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageService {
    void addMessage(MessageBean message);

    List<MessageBean> getMessageList(int fromUserId, int toUserId);
}
