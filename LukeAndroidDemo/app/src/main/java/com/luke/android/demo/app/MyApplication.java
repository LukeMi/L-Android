package com.luke.android.demo.app;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.luke.android.demo.sessionlifecycle.MyActivityLifecycleCallbacks;

/**
 * Created by mzchen on 2016/10/23.
 */

public class MyApplication extends Application {
    private int MSG_REPEAT_TIME = 0x00;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("session", "定时任务");
            handler.sendEmptyMessageDelayed(MSG_REPEAT_TIME, 3000);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
        //Handler 定时功能；
        handler.sendEmptyMessageDelayed(MSG_REPEAT_TIME, 3000);
    }
}
