package com.wiseapis.chat.dao;

import com.wiseapis.chat.bean.MessageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MessageDao {
    void addMessage(@Param(value = "message") MessageBean message);

    List<MessageBean> getMessageHistory(@Param(value = "fromUserId") int fromUserId,
                                        @Param(value = "toUserId")int toUserId);
}