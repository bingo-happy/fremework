package com.fremework.bingo.bingorxretrofitfremework.Utils.Logger;

import android.util.Log;

import com.fremework.bingo.bingorxretrofitfremework.Utils.Logger.BingoLog.InterfaceBingoLog;

/**
 * Created by bingo on 2017/3/28.
 */

public class DevLog implements InterfaceBingoLog {

    @Override
    public void log(String tag, int level, Object... msg) {
        String message;
        if (msg != null && msg.length == 1) {
            if(msg[0].getClass().isInstance(Throwable.class))
                message = Log.getStackTraceString((Throwable)msg[0]);
            else message = msg[0].toString();
        } else {
            StringBuilder sb = new StringBuilder();
            if (msg != null) for (Object m : msg) {
                if(m.getClass().isInstance(Throwable.class))
                    sb.append("\n").append(Log.getStackTraceString((Throwable)m));
                else sb.append(m);
            }
            message = sb.toString();
        }
        Log.println(level, tag, message);
    }
}
