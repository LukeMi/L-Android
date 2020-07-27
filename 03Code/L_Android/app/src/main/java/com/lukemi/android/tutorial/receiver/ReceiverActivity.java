package com.lukemi.android.tutorial.receiver;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.lukemi.android.common.util.DeviceUtil;
import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

public class ReceiverActivity extends AppCompatActivity {

    public static final String TAG = ReceiverActivity.class.getSimpleName();

    public static final String CUSTOM_BROAD_CAST_RECEIVER = "custom.BroadcastReceiver";

    private static NetChangedReceiver mNetChangedReceiver;

    private LocalBroadcastReceiver mLocalBroadcastReceiver;

    private TextView tvLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        tvLocal = findViewById(R.id.tv_local);
        initNetChangedReceiver();
        registerLocalBroadcastReceiver();
        sendCustomBroadcastReceiver();
        Application application = getApplication();
        Context applicationContext = getApplicationContext();
        KLog.d("application 地址 = " + application + " ; applicationContext 地址 = " + applicationContext);
    }

    private void sendCustomBroadcastReceiver() {
        Intent intent = new Intent("custom.BroadcastReceiver");
        this.sendBroadcast(intent);
    }

    private void registerLocalBroadcastReceiver() {
        // 注册本地广播
        mLocalBroadcastReceiver = new LocalBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                KLog.d(TAG, action);
                tvLocal.setText("this value is from local broadcastReceiver\n" + intent.getStringExtra("local"));
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LocalBroadcastReceiver.TAG);
        LocalBroadcastManager.getInstance(this).registerReceiver(mLocalBroadcastReceiver, intentFilter);

        KLog.d(TAG, "发送本地广播开始");
        Intent intent = new Intent(LocalBroadcastReceiver.TAG);
        intent.putExtra("local", "this is local broadcast receiver value");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        KLog.d(TAG, "发送本地广播结束");
    }


    /**
     * 注册网络改变广播
     */
    private void initNetChangedReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        // 为BroadcastReceiver指定action，使之用于接收同action的广播(网络变化)
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mNetChangedReceiver = new NetChangedReceiver();
        registerReceiver(mNetChangedReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        if (mNetChangedReceiver != null) {
            unregisterReceiver(mNetChangedReceiver);
        }
        if (mLocalBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mLocalBroadcastReceiver);
        }
        super.onDestroy();
    }

    /**
     * 网络改变的广播
     */
    public class NetChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            KLog.d(TAG, "onReceive");
            String action = intent.getAction();
            switch (action) {
                case ConnectivityManager.CONNECTIVITY_ACTION:
                    KLog.d("----networkChanged---->" + "netWorkType: " + DeviceUtil.getNetType(ReceiverActivity.this));
                    Toast.makeText(getApplicationContext(), DeviceUtil.getNetType(ReceiverActivity.this), Toast.LENGTH_LONG).show();
                    break;
                case CUSTOM_BROAD_CAST_RECEIVER:
                    KLog.d(TAG, "CUSTOM_BROAD_CAST_RECEIVER ");
                    break;
            }
        }
    }
}
