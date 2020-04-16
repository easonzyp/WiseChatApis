package com.wiseapis.chat.service;

import com.wiseapis.chat.bean.MessageBean;
import org.springframework.stereotype.Component;

@Component
public interface MessageService {
    void addMessage(MessageBean message);
}
