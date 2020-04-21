package com.wise.develop.ChatApis.dao;

import com.wise.develop.ChatApis.bean.ApplyFriendInfoBean;
import com.wise.develop.ChatApis.bean.FriendBean;
import com.wise.develop.ChatApis.bean.RecentContactBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface FriendDao {

    void applyFriend(@Param(value = "fromUserId") int fromUserId, @Param(value = "toUserId") int toUserId);

    void agreeFriend(@Param(value = "fromUserId") int fromUserId, @Param(value = "toUserId") int toUserId);

    String getAddStatus(@Param(value = "fromUserId") int fromUserId, @Param(value = "toUserId") int toUserId);

    void addFriendShip(@Param(value = "list") List<Map<String, Object>> friendList);

    List<ApplyFriendInfoBean> getFriendApplyList(@Param(value = "toUserId") int toUserId);

    int getNoAddFriendCount(@Param(value = "toUserId") int toUserId);

    List<FriendBean> getFriendList(@Param(value = "userId") int userId);

    List<RecentContactBean> getRecentContactList(@Param(value = "userId") int userId);

    void deleteRecentContactInfo(@Param(value = "userId") int userId, @Param(value = "friendId") int friendId);

    void addRecentContactInfo(@Param(value = "list") List<Map<String, Object>> contactInfo);
}
