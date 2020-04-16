package com.wiseapis.chat.dao;

import com.wiseapis.chat.bean.MessageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MessageDao {
    void addMessage(@Param(value = "message") MessageBean message);
}