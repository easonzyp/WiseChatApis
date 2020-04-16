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
    public void applyFriend(int fromUserId, int toUserId) {
        friendDao.applyFriend(fromUserId, toUserId);
    }

    @Override
    public List<UserBean> getApplyRecordList(int toUserId) {
        return friendDao.getFriendApplyList(toUserId);
    }

    @Override
    public void agreeFriend(List<FriendBean> friendList) {
        friendDao.deleteApplyRecord(friendList.get(0).getUserId(), friendList.get(0).getFriendId());
        friendDao.addFriendShip(friendList);
    }

    @Override
    public List<UserBean> getFriendList(int userId) {
        return friendDao.getFriendList(userId);
    }
}
