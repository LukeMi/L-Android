package com.luke.android.demo.datechangereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.luke.android.demo.util.Logcat;
import com.luke.android.demo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeChangedActivity extends AppCompatActivity {

    private TextView oldTime_DateChangedText;
    private TextView updateTime_DateChangedText;
    private TextView durationTime_DateChangedText;
    private DateChangedReceiver dateChangedReceiver;
    private final int MSG_CURRENTTIME = 0x0001;
    //当前时间
    private long currentTime = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CURRENTTIME:
                    currentTime = System.currentTimeMillis();
                    break;
            }
            handler.sendEmptyMessageDelayed(MSG_CURRENTTIME, 1000);
        }
    };
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_changed);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
        initView();
        initReceiver();
        handler.sendEmptyMessage(MSG_CURRENTTIME);
    }

    /**
     * 初始化广播
     */
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);   //为BroadcastReceiver指定action，使之用于接收同action的广播
        dateChangedReceiver = new DateChangedReceiver();
        registerReceiver(dateChangedReceiver, intentFilter);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        oldTime_DateChangedText = ((TextView) findViewById(R.id.oldTime_DateChanged));
        updateTime_DateChangedText = ((TextView) findViewById(R.id.updateTime_DateChanged));
        durationTime_DateChangedText = ((TextView) findViewById(R.id.durationTime_DateChanged));
    }

    /**
     * 时间改变的广播，加以处理时间改变的逻辑
     */
    public class DateChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_TIME_CHANGED)) {
                handler.removeMessages(MSG_CURRENTTIME);
                oldTime_DateChangedText.setText(sdf.format(new Date(currentTime)));
                //改变后的时间
                Long timestamp = System.currentTimeMillis();
                String timestampStr = sdf.format(new Date(timestamp));
                updateTime_DateChangedText.setText(timestampStr);
                long delta = currentTime - timestamp - 28800000;
                
                durationTime_DateChangedText.setText(String.valueOf(currentTime - timestamp));
                Logcat.log(String.valueOf("currentTime - timestamp = duration : " + currentTime + "-" + timestamp + "=" + (currentTime - timestamp)));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dateChangedReceiver);
    }
}
