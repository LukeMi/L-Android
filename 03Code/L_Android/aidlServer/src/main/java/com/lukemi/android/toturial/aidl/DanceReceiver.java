package com.lukemi.android.toturial.aidl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.socks.library.KLog;

public class DanceReceiver extends BroadcastReceiver {

    private OnActionListener mOnActionListener;

    public final String TAG = DanceReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        KLog.d(TAG, "onReceive : " + action);
        if (TextUtils.equals(action, "dance.BroadcastReceiver") && mOnActionListener != null) {
            mOnActionListener.onAction(action);
        }
    }

    public void setOnActionListener(OnActionListener listener) {
        mOnActionListener = listener;
    }

    public interface OnActionListener {
        void onAction(String str);
    }
}
