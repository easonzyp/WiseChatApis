package com.wise.develop.ChatApis.service.impl;

import com.wise.develop.ChatApis.service.JwtService;
import com.wise.develop.ChatApis.service.UserService;
import com.wise.develop.ChatApis.bean.UserBean;
import com.wise.develop.ChatApis.dao.UserDao;
import com.wise.develop.ChatApis.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "UserService")
@Transactional
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
    public List<UserBean> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public void saveToken(String userToken, int id) {
        userDao.saveToken(userToken, id);
    }
}
