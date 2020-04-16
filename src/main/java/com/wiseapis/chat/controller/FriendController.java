package com.wiseapis.chat.controller;

import com.wiseapis.chat.base.Result;
import com.wiseapis.chat.base.ResultCode;
import com.wiseapis.chat.base.ResultGenerator;
import com.wiseapis.chat.bean.FriendBean;
import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.service.FriendService;
import com.wiseapis.chat.service.JwtService;
import com.wiseapis.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        int fromUserId = jwtService.getUserId();
        int toUserId = params.get("toUserId");

        UserBean userBean = userService.getUserById(toUserId);
        if (userBean == null) {
            return ResultGenerator.genFailResult(ResultCode.PARAM_ERROR, "用户不存在");
        }

        friendService.applyFriend(fromUserId, toUserId);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/agreeFriend", method = RequestMethod.POST)
    public Result agreeFriend(@RequestBody HashMap<String, Integer> params) {
        int fromUserId = params.get("fromUserId");
        int toUserId = jwtService.getUserId();
        String fromUserName = userService.getUserById(fromUserId).getUserName();
        String toUserName = userService.getUserById(toUserId).getUserName();

        List<FriendBean> friendList = new ArrayList<>();
        FriendBean friend1 = new FriendBean();
        friend1.setUserId(fromUserId);
        friend1.setFriendId(toUserId);
        friend1.setRemarkName(toUserName);
        friendList.add(friend1);

        FriendBean friend2 = new FriendBean();
        friend2.setUserId(toUserId);
        friend2.setFriendId(fromUserId);
        friend2.setRemarkName(fromUserName);
        friendList.add(friend2);

        for (int i = 0; i < friendList.size(); i++) {
            System.out.print(friendList.get(i).getUserId());
        }

        friendService.agreeFriend(friendList);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getFriendApplyList", method = RequestMethod.POST)
    public Result getFriendApplyList() {
        int toUserId = jwtService.getUserId();

        List<UserBean> userBean = friendService.getApplyRecordList(toUserId);
        return ResultGenerator.genSuccessResult(userBean);
    }

    @ResponseBody
    @RequestMapping(value = "/getFriendList", method = RequestMethod.POST)
    public Result getFriendList() {
        int userId = jwtService.getUserId();

        List<UserBean> userBean = friendService.getFriendList(userId);
        return ResultGenerator.genSuccessResult(userBean);
    }
}
