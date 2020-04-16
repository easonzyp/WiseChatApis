package com.wiseapis.chat.controller;

import com.wiseapis.chat.base.Result;
import com.wiseapis.chat.base.ResultGenerator;
import com.wiseapis.chat.bean.MessageBean;
import com.wiseapis.chat.interceptor.UserLoginToken;
import com.wiseapis.chat.service.JwtService;
import com.wiseapis.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    JwtService jwtService;

    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Result sendMessage(@RequestBody HashMap<String, Object> params) {
        int fromUserId = jwtService.getUserId();
        String content = (String) params.get("content");
        int messageStatus = (int) params.get("messageStatus");
        int toUserId = (int) params.get("toUserId");

        MessageBean message = new MessageBean();
        message.setContent(content);
        message.setMessageStatus(messageStatus);
        message.setFromUserId(fromUserId);
        message.setToUserId(toUserId);

        messageService.addMessage(message);

        return ResultGenerator.genSuccessResult();
    }

}
