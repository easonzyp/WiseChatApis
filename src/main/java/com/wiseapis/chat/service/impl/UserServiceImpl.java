package com.wiseapis.chat.service.impl;

import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.dao.UserDao;
import com.wiseapis.chat.service.UserService;
import com.wiseapis.chat.utils.RandomUtil;
import com.wiseapis.chat.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserBean login(String account, String password) {
        return userDao.login(account, password);
    }

    @Override
    public UserBean createUser(String userName, String password) {
        String nickname = RandomUtil.getNumLargeSmallLetter(10);
        userDao.createUser(nickname, userName, password);
        UserBean userBean = userDao.getUserByName(userName);
        String token = JwtService.token(userBean.getId());
        userDao.saveToken(token, userBean.getId());
        return userDao.getUserByName(userName);
    }

    @Override
    public UserBean getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public UserBean getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public void saveToken(String userToken, int id) {
        userDao.saveToken(userToken, id);
    }
}
