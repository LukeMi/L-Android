package com.lukemi.myandroid.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lukemi.myandroid.R;

public class LifecycleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_fragment);
    }

    
}
