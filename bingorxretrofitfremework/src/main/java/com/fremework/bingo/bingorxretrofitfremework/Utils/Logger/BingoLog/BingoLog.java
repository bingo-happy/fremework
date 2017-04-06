package com.fremework.bingo.bingorxretrofitfremework.Utils.Logger.BingoLog;

import android.util.Log;

import com.fremework.bingo.bingorxretrofitfremework.Utils.Logger.DevLog;
import com.fremework.bingo.bingorxretrofitfremework.Utils.Logger.LocalLog;

/**
 * Created by bingo on 2017/4/6.
 */

public class BingoLog {

    private static String TAG = "BLog_";
    private static final int LOG_PREFIX_LENGTH = TAG.length();
    private static final int MAX_LOG_TAG_LENGTH = 25;

    private static DevLog devLog ;
    private static LocalLog localLog;

    public static void newInstance(){
        if(devLog==null)devLog=new DevLog();
        if(localLog==null)localLog=new LocalLog();
    }

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return TAG + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return TAG + str;
    }

    public static void e(String tag, Object ...msg){
        log(tag, Log.ERROR, devLog, msg);
        log(tag, Log.ERROR, localLog, msg);
    }

    public static void d(String tag, Object ...msg){
        log(tag, Log.DEBUG, devLog, msg);
    }

    public static void w(String tag, Object ...msg){
        log(tag, Log.WARN, devLog, msg);
    }

    public static void i(String tag, Object ...msg){
        log(tag, Log.INFO,devLog, msg);
    }

    public static void v(String tag, Object ...msg){
        log(tag, Log.VERBOSE,devLog, msg);
    }

    private static void log(String tag, int level, InterfaceBingoLog IfbLog, Object... msg){
        if(IfbLog!=null) IfbLog.log(tag ,level,msg);
    }

}
