package com.jeferry.android.widget.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import com.jeferry.android.widget.R;
import com.lukemi.android.common.MainActivity;


public class NotificationActivity extends AppCompatActivity {

    private static final String TAG = NotificationActivity.class.getSimpleName();

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initData(savedInstanceState);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_notice).setOnClickListener(this::onClick);
        findViewById(R.id.btn_remote).setOnClickListener(this::onClick);
    }

    protected void initData(Bundle savedInstanceState) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_notice) {
            notice();
        } else if (id == R.id.btn_remote) {
            remote(this);
        }
    }

    private void notice() {
        NotificationUtil.showNotification(this);
    }

    private void remote(Context context) {
        String CHANNEL_ID = "CHANNEL_ID";
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.remote_view);
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        NotificationManagerCompat compat = NotificationManagerCompat.from(getApplication());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("remoteContentTitle")
//                .setContentText("remoteContentText")
                .setContentIntent(pendingIntent)
//                .setContent(remoteViews)
//                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "name";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            String Description = "Description";
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//            mChannel.setDescription(Description);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.RED);
//            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100/*, 200, 300, 400, 500, 400, 300, 200, 400*/});
//            mChannel.setShowBadge(false);
            manager.createNotificationChannel(mChannel);
//            compat.(mChannel);
        }
        manager.notify(1, notification);
    }
}
