package com.wiseapis.chat.dao;

import com.wiseapis.chat.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserDao {

    void createUser(@Param(value = "userName") String userName,
                    @Param(value = "password") String password,
                    @Param(value = "userToken") String userToken);

    UserBean login(@Param(value = "userName") String userName,
                   @Param(value = "password") String password);

    UserBean getUserByName(@Param(value = "userName") String userName);
}
