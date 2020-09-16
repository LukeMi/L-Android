package com.lukemi.android.toturial.aidl;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AidlActivity extends AppCompatActivity {

    private TextView mTvReceiver;

    private DanceReceiver danceReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        mTvReceiver = findViewById(R.id.tv_aidl);
        register();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();
    }

    private void register() {
        danceReceiver = new DanceReceiver();
        danceReceiver.setOnActionListener(mTvReceiver::setText);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("dance.BroadcastReceiver");
        registerReceiver(danceReceiver, intentFilter);
    }

    private void unRegister() {
        if (danceReceiver != null) {
            unregisterReceiver(danceReceiver);
        }
    }

}
