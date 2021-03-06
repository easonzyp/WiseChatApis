package com.wise.develop.WiseChatApis.service.impl;

import com.wise.develop.WiseChatApis.service.JwtService;
import com.wise.develop.WiseChatApis.service.UserService;
import com.wise.develop.WiseChatApis.bean.UserBean;
import com.wise.develop.WiseChatApis.dao.UserDao;
import com.wise.develop.WiseChatApis.utils.RandomUtil;
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
        String nickName = RandomUtil.getNumLargeSmallLetter(10);
        userDao.createUser(nickName, userName, password);
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

    @Override
    public void updateUser(UserBean userInfo) {
        userDao.updateUser(userInfo);
    }
}
