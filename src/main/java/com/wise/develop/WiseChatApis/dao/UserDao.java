package com.wise.develop.WiseChatApis.dao;

import com.wise.develop.WiseChatApis.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserDao {

    void createUser(@Param(value = "nickname") String nickname,
                    @Param(value = "userName") String userName,
                    @Param(value = "password") String password);

    UserBean login(@Param(value = "userName") String userName,
                   @Param(value = "password") String password);

    UserBean getUserByName(@Param(value = "userName") String userName);

    UserBean getUserById(@Param(value = "id") int id);

    List<UserBean> getUserList();

    void saveToken(@Param(value = "userToken") String userToken, @Param(value = "id") int id);
}
