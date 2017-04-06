package com.fremework.bingo.bingorxretrofitfremework.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by bingo on 2017/4/6.
 */

public class GsonUtils {

    private static String defaultStringFormat = "yyyy-MM-dd HH:mm:ss";
    public static Gson getGson() {
        return new GsonBuilder().setDateFormat(defaultStringFormat).create();
    }

    public static Gson getGson(String dateFormat) {
        return new GsonBuilder().setDateFormat(dateFormat).create();
    }
}
