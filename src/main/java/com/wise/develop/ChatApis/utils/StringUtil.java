package com.wise.develop.ChatApis.utils;

import org.springframework.lang.Nullable;

public class StringUtil {
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
