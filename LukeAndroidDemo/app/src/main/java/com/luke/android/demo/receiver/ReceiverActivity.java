package com.luke.android.demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luke.android.demo.R;
import com.luke.android.demo.util.DeviceUtil;
import com.luke.android.demo.util.Logcat;

public class ReceiverActivity extends AppCompatActivity {

    private static NetChangedReceiver netChangedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        initNetChangedReceiver();
    }


    /**
     * 注册网络改变广播
     */
    private   void initNetChangedReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION); // 为BroadcastReceiver指定action，使之用于接收同action的广播(网络变化)
        netChangedReceiver = new NetChangedReceiver();
        registerReceiver(netChangedReceiver, intentFilter);
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
                    break;
            }

        }
    }
}
