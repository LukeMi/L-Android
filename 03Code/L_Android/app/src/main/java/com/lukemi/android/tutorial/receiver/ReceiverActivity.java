package com.lukemi.android.tutorial.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lukemi.android.tutorial.util.DeviceUtil;
import com.lukemi.android.common.util.Logcat;

public class ReceiverActivity extends AppCompatActivity {

    private static NetChangedReceiver netChangedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.tutorial.R.layout.activity_receiver);
        initNetChangedReceiver();
    }


    /**
     * 注册网络改变广播
     */
    private   void initNetChangedReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        // 为BroadcastReceiver指定action，使之用于接收同action的广播(网络变化)
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        netChangedReceiver = new NetChangedReceiver();
        registerReceiver(netChangedReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(netChangedReceiver);
        super.onDestroy();
    }

    /**
     * 网络改变的广播
     */
    public class NetChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ConnectivityManager.CONNECTIVITY_ACTION:
                    Logcat.log("----networkChanged---->"+"netWorkType: "+ DeviceUtil.getNetType(ReceiverActivity.this));
                    Toast.makeText(getApplicationContext(),DeviceUtil.getNetType(ReceiverActivity.this),Toast.LENGTH_LONG).show();
                    break;
            }

        }
    }
}
