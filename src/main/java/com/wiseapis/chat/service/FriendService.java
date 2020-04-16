package com.wiseapis.chat.service;

import com.wiseapis.chat.bean.FriendBean;
import com.wiseapis.chat.bean.UserBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FriendService {
    void applyFriend(int fromUserId, int toUserId);

    List<UserBean> getApplyRecordList(int toUserId);

    void agreeFriend(List<FriendBean> friendList);

    List<UserBean> getFriendList(int userId);
}
