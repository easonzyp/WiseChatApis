package com.wise.develop.WiseChatApis.controller;

import com.qiniu.util.Auth;
import com.wise.develop.WiseChatApis.base.Result;
import com.wise.develop.WiseChatApis.base.ResultGenerator;
import com.wise.develop.WiseChatApis.config.DataConfig;
import com.wise.develop.WiseChatApis.service.JwtService;
import com.wise.develop.WiseChatApis.utils.QinNiuOss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String upToken = QinNiuOss.getQiNiuToken();
        Map<String, String> params = new HashMap<>();
        params.put("token", upToken);
        return ResultGenerator.genSuccessResult(params);
    }
}