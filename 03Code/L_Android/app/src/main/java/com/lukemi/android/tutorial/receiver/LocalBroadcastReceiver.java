package com.lukemi.android.tutorial.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.socks.library.KLog;

public class LocalBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = LocalBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        KLog.d(TAG, "onReceive ");
        String action = intent.getAction();
        switch (action) {
            case "localManager":
                KLog.d(TAG, "onReceive - action - localManager");
                break;
        }
    }
}
