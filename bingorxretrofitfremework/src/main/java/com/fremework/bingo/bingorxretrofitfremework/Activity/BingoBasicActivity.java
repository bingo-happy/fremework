package com.fremework.bingo.bingorxretrofitfremework.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.fremework.bingo.bingorxretrofitfremework.Utils.BingoConfigs;
import com.fremework.bingo.bingorxretrofitfremework.Utils.BingoLog;
import com.fremework.bingo.bingorxretrofitfremework.Utils.OutputLocalLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by bingo on 2017/3/28.
 */

public class BingoBasicActivity extends AppCompatActivity{

    private static String defaultStringFormat = "yyyy-MM-dd HH:mm:ss";
    protected String TAG = BingoLog.makeLogTag(getClass().getSimpleName());
    protected final OutputLocalLog outputLocalLog = new OutputLocalLog(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLifeCycle("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLifeCycle("onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLifeCycle("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLifeCycle("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLifeCycle("onDestroy");
    }

    protected Gson getGson() {
        return new GsonBuilder().setDateFormat(defaultStringFormat).create();
    }

    protected Gson getGson(String dateFormat) {
        return new GsonBuilder().setDateFormat(dateFormat).create();
    }

    protected void outputLog(String msg){
        outputLocalLog.print(msg);
    }

    protected void debugLog(String msg){
        BingoLog.v(TAG, String.format("--%s:", msg), getClass().getSimpleName());
    }

    protected void warningLog(String msg){
        BingoLog.v(TAG, String.format("--%s:", msg), getClass().getSimpleName());
    }

    private void showLifeCycle(String lifecycleStage) {
        if (BingoConfigs.SHOW_LIFE_CYCLE) {
            BingoLog.v(TAG, String.format("----%s:", lifecycleStage), getClass().getSimpleName());
        }
    }




}
