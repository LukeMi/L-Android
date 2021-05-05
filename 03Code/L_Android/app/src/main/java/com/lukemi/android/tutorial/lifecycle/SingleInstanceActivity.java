package com.lukemi.android.tutorial.lifecycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

public class SingleInstanceActivity extends AppCompatActivity {

    private final String TAG = SingleInstanceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        KLog.d(TAG, getTaskId());
    }
}
