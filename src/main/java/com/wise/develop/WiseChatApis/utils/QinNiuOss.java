package com.wise.develop.WiseChatApis.utils;

import com.qiniu.util.Auth;
import com.wise.develop.WiseChatApis.config.DataConfig;

public class QinNiuOss {
    private static Auth auth = Auth.create(DataConfig.QINIUACCESSKEY, DataConfig.QINIUSECRETKEY);

    public static String getQiNiuToken() {
        return auth.uploadToken("wise-chat");
    }
}
