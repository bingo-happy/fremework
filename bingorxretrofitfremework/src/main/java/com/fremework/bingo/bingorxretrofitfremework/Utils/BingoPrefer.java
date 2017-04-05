package com.fremework.bingo.bingorxretrofitfremework.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bingo on 2017/3/29.
 */

public class BingoPrefer {
    private static BingoPrefer sInstance;
    private static SharedPreferences mPref;
    private BingoPrefer(Context context, String spName) {
        mPref = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }


    public static BingoPrefer getInstance(Context context, String spName) {
        if (sInstance == null) {
            synchronized (BingoPrefer.class) {
                sInstance = new BingoPrefer(context, spName);
            }
        }
        return sInstance;
    }


    /**
     * 存字符串
     * @param String    key
     * @param value  value
     */
    public void putString(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }


    /**
     * 取字符串
     * @param String   key
     * @return      value
     */
    public String getString(String key) {
        return mPref.getString(key, "");
    }


    /**
     * 存boolean
     * @param String    key
     * @param value  value
     */
    public void putBoolean(String key, boolean value) {
        mPref.edit().putBoolean(key, value).apply();
    }


    /**
     * 取 boolean
     * @param String       key
     * @param defValue  default value
     * @return    boolean
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mPref.getBoolean(key, defValue);
    }


    /**
     * 删除 key
     * @param String   key
     */
    public static void remove(String key) {
        mPref.edit().remove(key).apply();
    }


    /**
     * 清空sp
     * @return  boolean
     */
    public static boolean clear() {
        return mPref.edit().clear().commit();
    }
}
