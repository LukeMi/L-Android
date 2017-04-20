package com.lukemi.android.collection.handler;

import android.os.Handler;
import android.os.Message;

/**
 * Created by mzchen on 2016/11/15.
 */

public class MyHandler extends Handler {
    private  Handmsg handmsg;
    public MyHandler(Handmsg handmsg){
        super();
        this.handmsg = handmsg;
    }

    @Override
    public void handleMessage(Message msg) {
        handmsg.handleMessage(msg);
    }
}
