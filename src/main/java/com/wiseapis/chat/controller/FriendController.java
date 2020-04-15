package com.wiseapis.chat.controller;

import com.wiseapis.chat.base.Result;
import com.wiseapis.chat.base.ResultCode;
import com.wiseapis.chat.base.ResultGenerator;
import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.service.FriendService;
import com.wiseapis.chat.service.JwtService;
import com.wiseapis.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;
    @Autowired
    JwtService jwtService;

    @ResponseBody
    @RequestMapping(value = "/applyFriend", method = RequestMethod.POST)
    public Result applyFriend(@RequestBody HashMap<String, Integer> params) {
        int toUserId = params.get("toUserId");

        UserBean userBean = userService.getUserById(toUserId);
        if (userBean == null) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "用户不存在");
        }

        friendService.applyFriend(toUserId);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/agreeFriend", method = RequestMethod.POST)
    public Result agreeFriend(int fromUserId) {
        int toUserId = jwtService.getUserId();

        friendService.agreeFriend(fromUserId, toUserId);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getFriendApplyList", method = RequestMethod.POST)
    public Result getFriendApplyList() {
        int toUserId = jwtService.getUserId();

        List<UserBean> userBean = friendService.getApplyRecordList(toUserId);
        return ResultGenerator.genSuccessResult(userBean);
    }
}
