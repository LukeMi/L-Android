package com.luke.android.demo.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luke.android.demo.R;
import com.luke.android.demo.util.Logcat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlerPostActivity extends AppCompatActivity implements View.OnClickListener {

    private final int MSG_CYCLE = 0x0001;
    private final int MSG_INHANDLER = 0x0002;
    private int count;
    private int count1;
    private int count2;
    private boolean isAsyncRun;
    private ExecutorService service = Executors.newFixedThreadPool(2);
    private Button pBTN;
    private Button insideHandlerBTN;
    private TextView showTV;
    private TextView showTV1;
    private TextView showTV2;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case MSG_CYCLE:
                    count1++;
                    showTV1.setText("当前计数 = " + count1);
                    handler.sendEmptyMessageDelayed(MSG_CYCLE, 1000);
                    break;
            }
        }
    };
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            count++;
            showTV.setText("当前计数 = " + count);
            handler.postDelayed(this, 1000);
        }
    };


    public void createInnerHandler() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Handler innerHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        int what = msg.what;
                        switch (what) {
                            case MSG_INHANDLER:
                                count2++;
                                showTV2.setText("当前计数 = " + count2);
                                this.sendEmptyMessageDelayed(MSG_INHANDLER, 1000);
                                Logcat.log("----createInnerHandler----coming---->>" + count2);
                                break;
                        }
                    }
                };
                if (!innerHandler.hasMessages(MSG_INHANDLER)) {
                    innerHandler.sendEmptyMessageDelayed(MSG_INHANDLER, 1000);
                }
            }
        });
        thread.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_post);
        initView();

    }

    private void initView() {
        pBTN = ((Button) findViewById(R.id.press_HandlerPostActivity));
        insideHandlerBTN = ((Button) findViewById(R.id.insideHandler));
        pBTN.setOnClickListener(this);
        insideHandlerBTN.setOnClickListener(this);
        showTV = ((TextView) findViewById(R.id.showResult_HandlerPostActivity));
        showTV1 = ((TextView) findViewById(R.id.showResult1_HandlerPostActivity));
        showTV2 = ((TextView) findViewById(R.id.receiverInsideHandlerMSG));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.press_HandlerPostActivity:
                handler.postDelayed(mRunnable, 1000);
                if (!handler.hasMessages(MSG_CYCLE)) {
                    handler.sendEmptyMessageDelayed(MSG_CYCLE, 1000);
                }
                break;
            case R.id.insideHandler:
                if (!isAsyncRun) {
                    isAsyncRun = true;
                    createInnerHandler();
                }
                break;
        }
    }
}
