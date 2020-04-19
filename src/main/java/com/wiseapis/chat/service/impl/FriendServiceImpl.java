package com.wiseapis.chat.service.impl;

import com.wiseapis.chat.bean.ApplyFriendInfoBean;
import com.wiseapis.chat.bean.FriendBean;
import com.wiseapis.chat.bean.RecentContactBean;
import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.dao.FriendDao;
import com.wiseapis.chat.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    public String getAddStatus(int fromUserId, int toUserId) {
        return friendDao.getAddStatus(fromUserId, toUserId);
    }

    @Override
    public List<ApplyFriendInfoBean> getApplyRecordList(int toUserId) {
        return friendDao.getFriendApplyList(toUserId);
    }

    @Override
    public int getNoAddFriendCount(int toUserId) {
        return friendDao.getNoAddFriendCount(toUserId);
    }

    @Override
    public void agreeFriend(List<Map<String, Object>> friendList) {
        friendDao.agreeFriend((int) friendList.get(0).get("userId"), (int) friendList.get(0).get("friendId"));
        friendDao.addFriendShip(friendList);
    }

    @Override
    public List<FriendBean> getFriendList(int userId) {
        return friendDao.getFriendList(userId);
    }

    @Override
    public List<RecentContactBean> getRecentContactList(int userId) {
        return friendDao.getRecentContactList(userId);
    }

    @Override
    public void deleteRecentContactInfo(int userId, int friendId) {
        friendDao.deleteRecentContactInfo(userId, friendId);
    }

    @Override
    public void addRecentContactInfo(int userId, int friendId, List<Map<String, Object>> contactInfo) {
        deleteRecentContactInfo(userId, friendId);
        friendDao.addRecentContactInfo(contactInfo);
    }
}
