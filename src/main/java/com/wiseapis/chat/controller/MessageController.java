package com.wiseapis.chat.controller;

import com.wiseapis.chat.base.Result;
import com.wiseapis.chat.base.ResultGenerator;
import com.wiseapis.chat.bean.MessageBean;
import com.wiseapis.chat.bean.UserBean;
import com.wiseapis.chat.interceptor.UserLoginToken;
import com.wiseapis.chat.service.ChatService;
import com.wiseapis.chat.service.JwtService;
import com.wiseapis.chat.service.MessageService;
import com.wiseapis.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    ChatService chatService;

    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Result sendMessage(@RequestBody HashMap<String, Object> params) {
        int fromUserId = jwtService.getUserId();
        String content = (String) params.get("content");
        int toUserId = (int) params.get("toUserId");

        MessageBean message = new MessageBean();
        message.setContent(content);
        message.setFromUserId(fromUserId);
        message.setToUserId(toUserId);
        messageService.addMessage(message);

//        Map<String, Object> messageMap = new HashMap<>();
//        messageMap.put("content", content);
//        messageMap.put("time", messageTemp.getMessageTime());

        chatService.sendToUser(String.valueOf(toUserId), content);
        return ResultGenerator.genSuccessResult();
    }

    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/getMessageHistory", method = RequestMethod.POST)
    public Result getMessageHistory(@RequestBody HashMap<String, Object> params) {
        int fromUserId = jwtService.getUserId();
        int toUserId = (int) params.get("userId");

        List<MessageBean> messageListTemp = messageService.getMessageList(fromUserId, toUserId);
        //messageStatus 0：发送    1：接收
        List<Map<String, Object>> messageList = new ArrayList<>();
        for (MessageBean messageTemp : messageListTemp) {
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("content", messageTemp.getContent());
            messageMap.put("time", messageTemp.getMessageTime());
            UserBean userBean;
            int toUserIdTemp = messageTemp.getToUserId();
            if (toUserIdTemp == toUserId) {
                //代表是我发的
                userBean = userService.getUserById(fromUserId);
                messageMap.put("userName", userBean.getUserName());
                messageMap.put("messageStatus", 0);
                messageList.add(messageMap);
            } else if (toUserIdTemp == fromUserId) {
                //代表是我接的
                userBean = userService.getUserById(toUserId);
                messageMap.put("userName", userBean.getUserName());
                messageMap.put("messageStatus", 1);
                messageList.add(messageMap);
            }
        }

        return ResultGenerator.genSuccessResult(messageList);
    }
}