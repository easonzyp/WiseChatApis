package com.wiseapis.chat.controller;

import com.wiseapis.chat.base.Result;
import com.wiseapis.chat.base.ResultCode;
import com.wiseapis.chat.base.ResultGenerator;
import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.interceptor.UserLoginToken;
import com.wiseapis.chat.service.JwtService;
import com.wiseapis.chat.service.UserService;
import com.wiseapis.chat.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final
    UserService userService;
    private final
    JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody HashMap<String, String> params) {
        String userName = params.get("userName");
        String password = params.get("password");
        if (StringUtil.isEmpty(userName)) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "用户名不能为空");
        }
        if (StringUtil.isEmpty(password)) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "密码不能为空");
        }

        UserBean userBean = userService.getUserByName(userName);
        if (userBean != null) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "用户已存在");
        }

        userBean = userService.createUser(userName, password);
        return ResultGenerator.genSuccessResult(userBean);
    }

    @ResponseBody
    @RequestMapping("/login")
    public Result login(@RequestBody UserBean user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        if (StringUtil.isEmpty(userName)) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "用户名不能为空");
        }
        if (StringUtil.isEmpty(password)) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "密码不能为空");
        }
        UserBean userBean = userService.getUserByName(userName);
        if (userBean == null) {
            return ResultGenerator.genFailResult(ResultCode.USER_NOT_FOUND, "用户不存在");
        }

        userBean = userService.login(userName, password);
        if (userBean == null) {
            return ResultGenerator.genFailResult(ResultCode.PASSWORD_ERROR, "用户名或密码错误");
        }

        return ResultGenerator.genSuccessResult(userBean);
    }

    @UserLoginToken
    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    public Result searchUser(@RequestBody HashMap<String, String> params) {
        String userName = params.get("userName");
        List<UserBean> userList = new ArrayList<>();
        if (StringUtil.isEmpty(userName)) {
            userList.addAll(userService.getUserList());
        } else {
            userList.add(userService.getUserByName(userName));
        }
        return ResultGenerator.genSuccessResult(userList);
    }
}