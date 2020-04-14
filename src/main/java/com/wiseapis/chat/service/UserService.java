package com.wiseapis.chat.service;

import com.wiseapis.chat.bean.UserBean;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    UserBean login(String userName, String password);

    UserBean createUser(String userName, String password, String userToken);

    UserBean getUserByName(String userName);
}
