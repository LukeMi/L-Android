package com.lukemi.android.tutorial.handler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.handler.weak.WeakReferenceActivity;

public class HandlerTestActivity extends BaseHandlerActivity {

    private final int MSG_TEST = 0x0000;

    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        findViewById(R.id.tv_weak).setOnClickListener(this::onClick);
        mHandler = new MyHandler(this);
        mHandler.sendMessage(mHandler.obtainMessage(MSG_TEST, "handler"));
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_weak) {
            startActivity(new Intent(this, WeakReferenceActivity.class));
        }
    }

    @Override
    public void handleMessage(Message message) {
        super.handleMessage(message);
        int what = message.what;
        switch (what) {
            case MSG_TEST:
                String str = String.valueOf(message.what);
                Logcat.log("HandlerTestActivity " + str);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
