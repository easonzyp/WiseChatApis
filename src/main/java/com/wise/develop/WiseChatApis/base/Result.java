package com.wise.develop.WiseChatApis.base;

import com.alibaba.fastjson.JSON;

/**
 * @Description API接口通用返回模型
 * @author zhang
 * @param <T>
 */
public class Result<T> {
    private int code;
    private String message;
    private Object data;


    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
