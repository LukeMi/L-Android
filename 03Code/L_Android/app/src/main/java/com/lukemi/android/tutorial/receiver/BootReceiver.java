package com.lukemi.android.tutorial.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lukemi.android.tutorial.service.ForegroundService;
import com.lukemi.android.common.util.Logcat;
import com.socks.library.KLog;

public class BootReceiver extends BroadcastReceiver {

    public static final String TAG = BootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.lukemi.foregroundservice.destroy")
                || intent.getAction().equals("android.intent.action.BOOT_COMPLETED")
                || intent.getAction().equals("android.intent.action.USER_PRESENT")
                || intent.getAction().equals("android.intent.action.PACKAGE_RESTARTED")
                || intent.getAction().equals("android.intent.action.SCREEN_ON")
                || intent.getAction().equals("android.intent.action.SCREEN_OF")
        ) {
            //在这里写重新启动service的相关操作
            KLog.d(TAG , "onReceive" );
            startUploadService(context);
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 开启服务
     *
     * @param context
     */
    private void startUploadService(Context context) {
        Intent intent = new Intent(context, ForegroundService.class);
        context.startService(intent);
    }
}
