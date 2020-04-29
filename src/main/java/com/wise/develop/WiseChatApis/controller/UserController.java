package com.wise.develop.WiseChatApis.controller;

import com.wise.develop.WiseChatApis.service.JwtService;
import com.wise.develop.WiseChatApis.service.UserService;
import com.wise.develop.WiseChatApis.utils.DateUtil;
import com.wise.develop.WiseChatApis.utils.StringUtil;
import com.wise.develop.WiseChatApis.base.Result;
import com.wise.develop.WiseChatApis.base.ResultCode;
import com.wise.develop.WiseChatApis.base.ResultGenerator;
import com.wise.develop.WiseChatApis.bean.UserBean;
import com.wise.develop.WiseChatApis.interceptor.UserLoginToken;
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

        String token = JwtService.token(userBean.getId());
        userService.saveToken(token, userBean.getId());
        userBean = userService.getUserByName(userName);

        return ResultGenerator.genSuccessResult(userBean);
    }

    @UserLoginToken
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public Result getUserInfo() {
        int userId = jwtService.getUserId();
        return ResultGenerator.genSuccessResult(userService.getUserById(userId));
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

    @UserLoginToken
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Result updateUser(@RequestBody HashMap<String, Object> params) {
        int userId = jwtService.getUserId();
        String userHeader = (String) params.get("userHeader");
        String nickName = (String) params.get("nickName");
        int sex = (int) params.get("sex");
        String userLocation = (String) params.get("userLocation");
        String desc = (String) params.get("desc");
        String birth = (String) params.get("birth");
        if (StringUtil.isEmpty(userHeader)) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "头像不能为空");
        }
        if (StringUtil.isEmpty(nickName)) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "昵称不能为空");
        }
        if (StringUtil.isEmpty(userLocation)) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "地区不能为空");
        }
        if (StringUtil.isEmpty(birth)) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "出生日期不能为空");
        }
        UserBean userBean = new UserBean();
        userBean.setId(userId);
        userBean.setUserHeader(userHeader);
        userBean.setNickName(nickName);
        userBean.setSex(sex);
        userBean.setAge(DateUtil.getAge(birth));
        userBean.setUserLocation(userLocation);
        userBean.setBirth(birth);
        userBean.setDesc(StringUtil.isEmpty(desc) ? "暂无介绍" : desc);
        userService.updateUser(userBean);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://192.168.31.17:8080", "null"})
    public Result test() {
        List<UserBean> userList = new ArrayList<>();
        userList.addAll(userService.getUserList());
        return ResultGenerator.genSuccessResult(userList);
    }
}