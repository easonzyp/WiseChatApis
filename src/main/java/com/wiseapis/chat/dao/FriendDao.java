package com.wiseapis.chat.dao;

import com.wiseapis.chat.bean.FriendBean;
import com.wiseapis.chat.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FriendDao {

    void applyFriend(@Param(value = "fromUserId") int fromUserId,@Param(value = "toUserId") int toUserId);

    void deleteApplyRecord(@Param(value = "fromUserId") int fromUserId, @Param(value = "toUserId") int toUserId);

    void addFriendShip(@Param(value = "friendList") List<FriendBean> friendList);

    List<UserBean> getFriendApplyList(@Param(value = "toUserId") int toUserId);

    List<UserBean> getFriendList(@Param(value = "userId") int userId);
}
