package com.wise.develop.WiseChatApis.base;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200),                   //成功
    FAIL(400),                      //失败
    UNAUTHORIZED(401),              //未认证（签名错误）
    NOT_FOUND(404),                 //接口不存在
    INTERNAL_SERVER_ERROR(500),     //服务器内部错误

    //自定义错误类型
    PARAM_ERROR(1005),                  //参数错误
    USER_NOT_FOUND(1006),               //用户不存在
    PASSWORD_ERROR(1007);               //用户不存在

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
