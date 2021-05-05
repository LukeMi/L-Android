package com.lukemi.android.tutorial.handler;

import android.os.Bundle;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;


public class BaseHandlerActivity extends AppCompatActivity implements Handmsg{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void handleMessage(Message message) {
        
    }
}
