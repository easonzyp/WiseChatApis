package com.wiseapis.chat.service.impl;

import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.dao.UserDao;
import com.wiseapis.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "loginService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserBean login(String account, String password) {
        return userDao.login(account, password);
    }

    @Override
    public UserBean createUser(String userName, String password, String userToken) {
        userDao.createUser(userName, password, userToken);
        return userDao.getUserByName(userName);
    }

    @Override
    public UserBean getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }
}
