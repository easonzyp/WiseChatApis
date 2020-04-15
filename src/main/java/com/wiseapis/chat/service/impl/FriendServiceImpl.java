package com.wiseapis.chat.service.impl;

import com.wiseapis.chat.bean.FriendBean;
import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.dao.FriendDao;
import com.wiseapis.chat.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "FriendService")
@Transactional
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendDao friendDao;

    @Override
    public void applyFriend(int toUserId) {
        friendDao.applyFriend(toUserId);
    }

    @Override
    public List<UserBean> getApplyRecordList(int toUserId) {
        return friendDao.getFriendApplyList(toUserId);
    }

    @Override
    public void agreeFriend(int fromUserId, int toUserId) {
        friendDao.deleteApplyRecord(fromUserId, toUserId);
        friendDao.agreeFriend(toUserId, fromUserId);
    }

    @Override
    public List<FriendBean> getFriendList(int userId) {
        return null;
    }
}
