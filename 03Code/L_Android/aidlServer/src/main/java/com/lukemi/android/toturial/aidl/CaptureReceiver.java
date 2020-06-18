package com.lukemi.android.toturial.aidl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.socks.library.KLog;

public class CaptureReceiver extends BroadcastReceiver {

    public static final String TAG = CaptureReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        KLog.d(TAG, "onReceive : " + intent.getAction());
    }
}
