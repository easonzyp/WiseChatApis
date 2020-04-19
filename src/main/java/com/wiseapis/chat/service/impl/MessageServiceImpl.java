package com.wiseapis.chat.service.impl;

import com.wiseapis.chat.bean.MessageBean;
import com.wiseapis.chat.dao.MessageDao;
import com.wiseapis.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "MessageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    @Override
    public void addMessage(MessageBean message) {
        messageDao.addMessage(message);
    }

    @Override
    public List<MessageBean> getMessageList(int fromUserId, int toUserId) {
        return messageDao.getMessageHistory(fromUserId,toUserId);
    }
}
