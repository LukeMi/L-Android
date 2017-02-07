package com.tbug.android.demo.handler;

import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.tbug.android.demo.util.Logcat;

public class HandlerTestActivity extends BaseHandlerActivity {
    private final int MSG_TEST = 0x0000;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myHandler = new MyHandler(this);
        myHandler.sendMessage(myHandler.obtainMessage(MSG_TEST,"handler"));
    }

    @Override
    public void handleMessage(Message message) {
        super.handleMessage(message);
        int what = message.what;
        switch(what){
            case MSG_TEST:
                 String str = String.valueOf(message.what);
                Logcat.log("HandlerTestActivity "+str);
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
