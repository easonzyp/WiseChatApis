package com.wise.develop.ChatApis.service;

import com.wise.develop.ChatApis.bean.MessageBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageService {
    void addMessage(MessageBean message);

    List<MessageBean> getMessageList(int fromUserId, int toUserId);

    void clearUnReadMsgCount(int fromUserId, int toUserId);
}
