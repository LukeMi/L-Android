package com.lukemi.android.tutorial.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.jeferry.android.widget.R;
import com.lukemi.android.tutorial.util.NotificationUtil;


public class NotificationActivity extends AppCompatActivity {

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initData(savedInstanceState);
        initView();
    }

    private void initView() {

        findViewById(R.id.btn_notice).setOnClickListener(this::onViewClicked);
        findViewById(R.id.btn_remote).setOnClickListener(this::onViewClicked);
    }

    protected void initData(Bundle savedInstanceState) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }


    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_notice) {
            notice();
        } else if (id == R.id.btn_remote) {
            remote(this);
        }
    }

    private void notice() {
      /*  Bundle bundle = new Bundle();
        bundle.putCharSequence("key","key");
        bundle.putCharSequence("value","value");
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("标题")
                .setContentText("内容，，，，，，")
                .setAutoCancel(true);
        Notification build = builder.*/

        NotificationUtil.showNotification(this);
    }

    private void remote(Context context) {
//        Intent intent = new Intent(context, VolumeActivity.class);
//        startActivity(intent);
    }
}
