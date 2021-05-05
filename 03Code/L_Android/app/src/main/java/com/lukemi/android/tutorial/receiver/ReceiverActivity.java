package com.lukemi.android.tutorial.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.lukemi.android.common.util.DeviceUtil;
import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

public class ReceiverActivity extends AppCompatActivity {

    public static final String TAG = ReceiverActivity.class.getSimpleName();

    public static final String CUSTOM_BROAD_CAST_RECEIVER = "custom.BroadcastReceiver";

    private static CurrentReceiver mCurrentReceiver;

    private LocalBroadcastReceiver mLocalBroadcastReceiver;

    private TextView tvLocal;

    private TextView mTvReceiveNet;

    private TextView mTvReceiverSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        initView();
        registerLocalBroadcastReceiver();
    }

    private void initView() {
        tvLocal = findViewById(R.id.tv_register_local);
        mTvReceiveNet = findViewById(R.id.tv_receiver_net);
        mTvReceiverSms = findViewById(R.id.tv_receiver_sms);

        findViewById(R.id.tv_register).setOnClickListener(this::onClick);
        findViewById(R.id.tv_send_custom_broadcast_receiver).setOnClickListener(this::onClick);
        tvLocal.setOnClickListener(this::onClick);
        findViewById(R.id.tv_send_out_app_receiver).setOnClickListener(this::onClick);
        mTvReceiveNet.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_register) {
            initNetChangedReceiver();
        } else if (id == R.id.tv_send_custom_broadcast_receiver) {
            Intent intent = new Intent(CUSTOM_BROAD_CAST_RECEIVER);
            sendBroadcast(intent);
        } else if (id == R.id.tv_register_local) {
            sendLocalBroadReceiver();
        } else if (id == R.id.tv_send_out_app_receiver) {
            sendIPCBroadcastReceiver();
        }

    }

    /**
     * 发送自定义广播
     */
    private void sendIPCBroadcastReceiver() {
        // 接收方为静态注册
        Intent intent = new Intent("aidl.BroadcastReceiver");
        // 必须加，不然android 8.0 以后收不到
        intent.setComponent(new ComponentName("com.lukemi.android.toturial.aidl", "com.lukemi.android.toturial.aidl.CaptureReceiver"));
        this.sendBroadcast(intent);

        // 接收方为动态动态
        Intent dance = new Intent("dance.BroadcastReceiver");
        // 不要使用component !!! 动态注册
        this.sendBroadcast(dance);
    }

    /**
     * 注册广播
     */
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
    }

    /**
     * 发送本地广播
     */
    private void sendLocalBroadReceiver() {
        // 发送本地广播
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
        intentFilter.addAction(CUSTOM_BROAD_CAST_RECEIVER);
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        mCurrentReceiver = new CurrentReceiver();
        registerReceiver(mCurrentReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        if (mCurrentReceiver != null) {
            unregisterReceiver(mCurrentReceiver);
        }
        if (mLocalBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mLocalBroadcastReceiver);
        }
        super.onDestroy();
    }

    /**
     * 网络改变的广播
     */
    public class CurrentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            KLog.d(TAG, "onReceive");
            String action = intent.getAction();
            switch (action) {
                // 网络改变
                case ConnectivityManager.CONNECTIVITY_ACTION:
                    String netType = DeviceUtil.getNetType(context);
                    KLog.d("----networkChanged---->" + "netWorkType: " + netType);
                    mTvReceiveNet.setText(netType);
//                    Toast.makeText(getApplicationContext(), DeviceUtil.getNetType(ReceiverActivity.this), Toast.LENGTH_LONG).show();
                    break;
                // 自定义广播
                case CUSTOM_BROAD_CAST_RECEIVER:
                    KLog.d(TAG, CUSTOM_BROAD_CAST_RECEIVER);
                    break;
                // 收到短信
                case "android.provider.Telephony.SMS_RECEIVED":
                    KLog.d(TAG, " 收到短信提醒");
                    mTvReceiverSms.setText("收到短信提醒");
                    break;
            }
        }
    }
}
