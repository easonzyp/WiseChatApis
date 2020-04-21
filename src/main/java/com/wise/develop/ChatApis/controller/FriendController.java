package com.wise.develop.ChatApis.controller;

import com.wise.develop.ChatApis.service.FriendService;
import com.wise.develop.ChatApis.service.JwtService;
import com.wise.develop.ChatApis.service.UserService;
import com.wise.develop.ChatApis.base.Result;
import com.wise.develop.ChatApis.base.ResultCode;
import com.wise.develop.ChatApis.base.ResultGenerator;
import com.wise.develop.ChatApis.bean.ApplyFriendInfoBean;
import com.wise.develop.ChatApis.bean.FriendBean;
import com.wise.develop.ChatApis.bean.RecentContactBean;
import com.wise.develop.ChatApis.bean.UserBean;
import com.wise.develop.ChatApis.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //查询记录表中是否有申请记录 如果有 则提示已申请过
        String addStatus = friendService.getAddStatus(fromUserId, toUserId);
        if (!StringUtil.isEmpty(addStatus)) {
            return ResultGenerator.genFailResult(ResultCode.FAIL, "已经申请过了");
        }

        if ("1".equals(addStatus)) {
            return ResultGenerator.genFailResult(ResultCode.FAIL, "已经是好友了");
        }

        friendService.applyFriend(fromUserId, toUserId);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/agreeFriend", method = RequestMethod.POST)
    public Result agreeFriend(@RequestBody HashMap<String, Integer> params) {
        int fromUserId = params.get("fromUserId");
        int toUserId = jwtService.getUserId();
        String addStatus = friendService.getAddStatus(fromUserId, toUserId);
        if ("1".equals(addStatus)) {
            return ResultGenerator.genFailResult(ResultCode.FAIL, "已经同意过了");
        }

        String fromUserName = userService.getUserById(fromUserId).getUserName();
        String toUserName = userService.getUserById(toUserId).getUserName();

        List<Map<String, Object>> friendList = new ArrayList<>();
        Map<String, Object> fMap1 = new HashMap<>();
        fMap1.put("userId", fromUserId);
        fMap1.put("friendId", toUserId);
        fMap1.put("remarkName", toUserName);
        friendList.add(fMap1);

        Map<String, Object> fMap2 = new HashMap<>();
        fMap2.put("userId", toUserId);
        fMap2.put("friendId", fromUserId);
        fMap2.put("remarkName", fromUserName);
        friendList.add(fMap2);

        friendService.agreeFriend(friendList);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getFriendApplyCount", method = RequestMethod.POST)
    public Result getFriendApplyCount() {
        int toUserId = jwtService.getUserId();
        int count = friendService.getNoAddFriendCount(toUserId);
        Map<String, Object> response = new HashMap<>();
        response.put("applyCount", count);
        return ResultGenerator.genSuccessResult(response);
    }

    @ResponseBody
    @RequestMapping(value = "/getFriendApplyList", method = RequestMethod.POST)
    public Result getFriendApplyList() {
        int toUserId = jwtService.getUserId();
        List<ApplyFriendInfoBean> userBean = friendService.getApplyRecordList(toUserId);
        return ResultGenerator.genSuccessResult(userBean);
    }

    @ResponseBody
    @RequestMapping(value = "/getFriendList", method = RequestMethod.POST)
    public Result getFriendList() {
        int userId = jwtService.getUserId();
        List<FriendBean> friendBean = friendService.getFriendList(userId);
        return ResultGenerator.genSuccessResult(friendBean);
    }

    @ResponseBody
    @RequestMapping(value = "/getRecentContactList", method = RequestMethod.POST)
    public Result getRecentContactList() {
        int userId = jwtService.getUserId();
        List<RecentContactBean> friendBean = friendService.getRecentContactList(userId);
        return ResultGenerator.genSuccessResult(friendBean);
    }
}