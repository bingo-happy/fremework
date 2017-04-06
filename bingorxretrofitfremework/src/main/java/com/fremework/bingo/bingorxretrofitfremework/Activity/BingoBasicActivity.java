package com.fremework.bingo.bingorxretrofitfremework.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fremework.bingo.bingorxretrofitfremework.Utils.DevConfig;
import com.fremework.bingo.bingorxretrofitfremework.Utils.Logger.BingoLog.BingoLog;

/**
 * Created by bingo on 2017/3/28.
 */

public class BingoBasicActivity extends AppCompatActivity{

    protected String TAG = BingoLog.makeLogTag(getClass().getSimpleName());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BingoLog.newInstance();
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


    private void showLifeCycle(String lifecycleStage) {
        if (DevConfig.SHOW_LIFE_CYCLE) {
            BingoLog.v(TAG, String.format("--%s:", lifecycleStage), getClass().getSimpleName());
        }
    }
}
