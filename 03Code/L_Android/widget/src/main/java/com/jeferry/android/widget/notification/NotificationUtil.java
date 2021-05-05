package com.jeferry.android.widget.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import static android.os.Build.VERSION_CODES.LOLLIPOP_MR1;

public class NotificationUtil {

    public static void showNotification(Context context) {

        final int NOTIFICATION_ID = 12234;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //准备intent
        Intent intent = new Intent();
        String action = "com.tamic.myapp.action";
        intent.setAction(action);

        //notification
        Notification notification = null;
        String contentText = null;
        // 构建 PendingIntent
        PendingIntent pi = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //版本兼容

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification();
            notification.icon = android.R.drawable.stat_sys_download_done;
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notification.contentIntent = pi;
//            notification.setLatestEventInfo(context, aInfo.mFilename, contentText, pi);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                Build.VERSION.SDK_INT <= LOLLIPOP_MR1) {
            notification = new Notification.Builder(context)

                    .setContentIntent(pi)
                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                    .setWhen(System.currentTimeMillis())
                    .build();
        } else if (Build.VERSION.SDK_INT >= LOLLIPOP_MR1 && Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context)
                    .setContentTitle("Title")
                    .setContentText(contentText)
                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                    .setContentIntent(pi).build();

        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100/*, 200, 300, 400, 500, 400, 300, 200, 400*/});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
            notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                    .setContentTitle("ContentTitle")
                    .setContentText("ContentText")
                    .setAutoCancel(true)
                    .setContentIntent(pi)
                    .build();
        }

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
