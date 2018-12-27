package com.lukemi.android.tutorial.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;
import com.lukemi.android.tutorial.util.NotificationUtil;
import com.lukemi.android.tutorial.volum.VolumeActivity;

import butterknife.OnClick;

public class NotificationActivity extends AbsBaseActivity {

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @OnClick({R.id.btn_notice, R.id.btn_remote})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_notice:
                notice();
                break;
            case R.id.btn_remote:
                remote(this);
                break;
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
        Intent intent = new Intent(context, VolumeActivity.class);
        startActivity(intent);
    }
}
