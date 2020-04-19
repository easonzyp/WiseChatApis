package com.wiseapis.chat.service;

import com.wiseapis.chat.bean.ApplyFriendInfoBean;
import com.wiseapis.chat.bean.FriendBean;
import com.wiseapis.chat.bean.RecentContactBean;
import com.wiseapis.chat.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface FriendService {
    void applyFriend(int fromUserId, int toUserId);

    String getAddStatus(int fromUserId, int toUserId);

    List<ApplyFriendInfoBean> getApplyRecordList(int toUserId);

    int getNoAddFriendCount(int toUserId);

    void agreeFriend(List<Map<String, Object>> friendList);

    List<FriendBean> getFriendList(int userId);

    List<RecentContactBean> getRecentContactList(int userId);

    void deleteRecentContactInfo(int userId, int friendId);

    void addRecentContactInfo(int userId, int friendId, List<Map<String, Object>> contactInfo);
}