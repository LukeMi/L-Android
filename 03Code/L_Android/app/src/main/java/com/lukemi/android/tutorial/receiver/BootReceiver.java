package com.lukemi.android.tutorial.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lukemi.android.tutorial.service.ForegroundService;
import com.lukemi.android.common.util.Logcat;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        if (intent.getAction().equals("com.lukemi.foregroundservice.destroy")
                || intent.getAction().equals("android.intent.action.BOOT_COMPLETED")
                || intent.getAction().equals("android.intent.action.USER_PRESENT")
                || intent.getAction().equals("android.intent.action.PACKAGE_RESTARTED")
                || intent.getAction().equals("android.intent.action.SCREEN_ON")
                || intent.getAction().equals("android.intent.action.SCREEN_OF")
                ) {
            //在这里写重新启动service的相关操作
            Logcat.log("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
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
