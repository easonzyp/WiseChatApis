package com.wise.develop.WiseChatApis.service;

import com.wise.develop.WiseChatApis.bean.MessageBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageService {
    void addMessage(MessageBean message);

    List<MessageBean> getMessageList(int fromUserId, int toUserId);

    void clearUnReadMsgCount(int fromUserId, int toUserId);
}
