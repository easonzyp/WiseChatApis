package com.wise.develop.WiseChatApis.controller;

import com.qiniu.util.Auth;
import com.wise.develop.WiseChatApis.base.Result;
import com.wise.develop.WiseChatApis.base.ResultGenerator;
import com.wise.develop.WiseChatApis.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    JwtService jwtService;

    @ResponseBody
    @RequestMapping(value = "/getUploadToken", method = RequestMethod.POST)
    public Result getUploadToken() {

        String accessKey = "lJQ2Xr5Ja3BwfqrAs0lRx8taPsFKp91L-Rgv1wUW";
        String secretKey = "fOBDMcucdL5sZS4MgKJ5Aa-A3Zj6oyEroYK_MlcN";
        String bucket = "wise-chat";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Map<String,String> params = new HashMap<>();
        params.put("token",upToken);
        return ResultGenerator.genSuccessResult(params);
    }
}