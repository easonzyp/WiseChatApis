package com.wiseapis.chat.dao;

import com.wiseapis.chat.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FriendDao {

    void applyFriend(@Param(value = "toUserId") int toUserId);

    void deleteApplyRecord(@Param(value = "fromUserId") int fromUserId, @Param(value = "toUserId") int toUserId);

    void agreeFriend(@Param(value = "userId") int userId, @Param(value = "friendId") int friendId);

    List<UserBean> getFriendApplyList(@Param(value = "toUserId") int toUserId);
}
