package com.lukemi.android.tutorial.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.app.Application;

/**
 * 时间改变的广播，加以处理时间改变的逻辑
 */
public class TimeChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_TIME_CHANGED)) {
            if (Application.handler.hasMessages(Application.MSG_CURRENT_TIME)) {
                Application.handler.removeMessages(Application.MSG_CURRENT_TIME);
            }
            //改变后的时间
            Long timestamp = System.currentTimeMillis();
            Logcat.log("---- duration = currentTime - timestamp ---- " + (Application.currentTime - timestamp) + "=" + Application.currentTime + "-" + timestamp);
            if (!Application.handler.hasMessages(Application.MSG_CURRENT_TIME)) {
                Application.handler.sendEmptyMessage(Application.MSG_CURRENT_TIME);
            }
        }
    }
}
