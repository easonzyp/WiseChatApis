package com.wise.develop.ChatApis.dao;

import com.wise.develop.ChatApis.bean.MessageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MessageDao {
    void addMessage(@Param(value = "message") MessageBean message);

    List<MessageBean> getMessageHistory(@Param(value = "fromUserId") int fromUserId,
                                        @Param(value = "toUserId") int toUserId);

    void clearUnReadMsgCount(@Param(value = "fromUserId") int fromUserId,
                             @Param(value = "toUserId") int toUserId);
}