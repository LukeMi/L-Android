package com.jeferry.android.widget;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {

    private static ProgressBar mProgressBar;

    private static final int MSG_COUNT = 0x000001;
    private static int count = 10;

    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_COUNT) {
                if (count == 90) {
                    mHandler.removeMessages(MSG_COUNT);
                } else {
                    count++;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mProgressBar.setProgress(count, true);
                    } else {
                        mProgressBar.setProgress(count);
                    }
                    launch();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

    }

    private void initView() {
        findViewById(R.id.btn_launch).setOnClickListener(this::onClick);
        mProgressBar = findViewById(R.id.progressBar);
    }

    private void onClick(View view) {
        if (view.getId() == R.id.btn_launch) {
            launch();
        }
    }

    private static void launch() {
        mHandler.sendEmptyMessageDelayed(MSG_COUNT, 1000);
    }

}
