package com.wise.develop.WiseChatApis.base;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static Result genFailResult(ResultCode errCode, String message) {
        return new Result()
                .setCode(errCode)
                .setMessage(message);
    }

    public static Result genFailResult(ResultCode errCode, String message, Object data) {
        return new Result()
                .setCode(errCode)
                .setMessage(message)
                .setData(data);
    }
}
