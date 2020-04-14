package com.wiseapis.chat.controller;

import com.wiseapis.chat.base.Result;
import com.wiseapis.chat.base.ResultCode;
import com.wiseapis.chat.base.ResultGenerator;
import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.dao.UserDao;
import com.wiseapis.chat.service.UserService;
import com.wiseapis.chat.utils.StringUtil;
import com.wiseapis.chat.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("/register")
    public Result register(@RequestBody UserBean user) {
        String userName = user.getUserName();
        String password = user.getPassword();
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
        String token = TokenUtils.token(userName, password);
        userService.createUser(userName, password, token);
        return ResultGenerator.genSuccessResult();
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
}
