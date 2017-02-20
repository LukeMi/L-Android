package com.tbug.android.collection_demo.handler;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseHandlerActivity extends AppCompatActivity implements Handmsg{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void handleMessage(Message message) {
        
    }
}
