package com.lukemi.android.tutorial.handler.weak;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

public class WeakReferenceActivity extends AppCompatActivity {

    private Button mBtnSend;

    private TextView mTvWeak;

    private WeakReferenceHandler weakReferenceHandler = new WeakReferenceHandler(this) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 500:
                    if (weakReferenceHandler.getReference() != null) {
                        mTvWeak.setText((CharSequence) msg.obj);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weak_reference);
        initView();
    }

    private void initView() {
        mBtnSend = findViewById(R.id.btn_send);
        mTvWeak = findViewById(R.id.tv_weak);
        mBtnSend.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_send) {
            Message message = Message.obtain();
            message.what = 500;
            message.obj = " 收到 500 消息";
            weakReferenceHandler.sendMessageDelayed(message, 3000);
        }
    }


}