package com.fremework.bingo.bingorxretrofitfremework.Utils;
import android.util.Log;

/**
 * Created by bingo on 2017/3/28.
 */

public class BingoLog {

    private static final String LOG_PREFIX = "Log_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 25;

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return LOG_PREFIX + str;
    }

    public static void v(String tag, Object... messages) {
        // Only log VERBOSE if build type is DEBUG
        if (DevConfig.SHOW_LOG) {
            log(tag, Log.VERBOSE, null, messages);
        }
    }


    public static void d(String tag, Object... messages) {
        // Only log DEBUG if build type is DEBUG
        if (DevConfig.SHOW_LOG) {
            log(tag, Log.DEBUG, null, messages);
        }
    }

    public static void w(String tag, Object... messages) {
        log(tag, Log.WARN, null, messages);
    }

    public static void w(String tag, Throwable t, Object... messages) {
        log(tag, Log.WARN, t, messages);
    }

    public static void e(String tag, Object... messages) {
        log(tag, Log.ERROR, null, messages);
    }

    public static void e(String tag, Throwable t, Object... messages) {
        log(tag, Log.ERROR, t, messages);
    }

    public static void i(String tag, Object... messages) {
        if (DevConfig.SHOW_LOG) {
            log(tag, Log.INFO, null, messages);
        }
    }

    private static void log(String tag, int level, Throwable t, Object... messages) {
        String message;
        if (t == null && messages != null && messages.length == 1) {
            // handle this common case without the extra cost of creating a string buffer:
            message = messages[0].toString();
        } else {
            StringBuilder sb = new StringBuilder();
            if (messages != null) for (Object m : messages) {
                sb.append(m);
            }
            if (t != null) {
                sb.append("\n").append(Log.getStackTraceString(t));
            }
            message = sb.toString();
        }
        Log.println(level, tag, message);
    }
}
