package com.lukemi.android.tutorial.lifecycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

public class NewTaskActivity extends AppCompatActivity {
    private final String TAG = NewTaskActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        KLog.d(TAG, getTaskId());
    }
}
