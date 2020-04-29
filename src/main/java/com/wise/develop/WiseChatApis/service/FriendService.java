package com.wise.develop.WiseChatApis.service;

import com.wise.develop.WiseChatApis.bean.ApplyFriendInfoBean;
import com.wise.develop.WiseChatApis.bean.FriendBean;
import com.wise.develop.WiseChatApis.bean.RecentContactBean;
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

    String getFriendRemarkName(int fromUserId, int toUserId);
}