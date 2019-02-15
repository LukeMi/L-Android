package com.lukemi.android.tutorial.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import com.lukemi.android.tutorial.MainActivity;
import com.lukemi.android.tutorial.R;

public class ForegroundService extends Service {
    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //从铃声管理器
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.app_icon)
                .setWhen(System.currentTimeMillis())
                .setTicker("有通知到来")
                .setContentTitle("这是通知的标题")
//                .setDefaults(Notification.DEFAULT_ALL)
                .setDefaults(/*Notification.DEFAULT_SOUND |*/ Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS|Notification.COLOR_DEFAULT)
                .setSound(sound)
                .setContentText("这是通知的内容")
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .build();

        /*使用startForeground,如果id为0，那么notification将不会显示*/
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(1,notification);
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        Intent intent = new Intent("com.lukemi.foregroundservice.destroy");
        sendBroadcast(intent);
        super.onDestroy();
    }
}
