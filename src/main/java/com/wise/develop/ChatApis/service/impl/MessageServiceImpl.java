package com.wise.develop.ChatApis.service.impl;

import com.wise.develop.ChatApis.service.MessageService;
import com.wise.develop.ChatApis.bean.MessageBean;
import com.wise.develop.ChatApis.dao.MessageDao;
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
        return messageDao.getMessageHistory(fromUserId, toUserId);
    }

    @Override
    public void clearUnReadMsgCount(int fromUserId, int toUserId) {
        messageDao.clearUnReadMsgCount(fromUserId, toUserId);
    }
}
