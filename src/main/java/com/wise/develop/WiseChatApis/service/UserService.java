package com.wise.develop.WiseChatApis.service;

import com.wise.develop.WiseChatApis.bean.UserBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    UserBean login(String userName, String password);

    UserBean createUser(String userName, String password);

    UserBean getUserByName(String userName);

    UserBean getUserById(int id);

    List<UserBean> getUserList();

    void saveToken(String userToken, int id);
}
