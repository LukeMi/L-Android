package com.lukemi.android.collection.utiltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UtilMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_util_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        findViewById(com.lukemi.android.collection.R.id.BitmapUtilActivity).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.DeviceutilActivity).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.ZipUtilActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?> targetClass = null;
        switch (v.getId()) {

            case com.lukemi.android.collection.R.id.BitmapUtilActivity:
                targetClass = BitmapUtilActivity.class;
                break;

            case com.lukemi.android.collection.R.id.DeviceutilActivity:
                targetClass = DeviceutilActivity.class;
                break;

            case com.lukemi.android.collection.R.id.ZipUtilActivity:
                targetClass = ZipUtilActivity.class;
                break;
        }
        if (targetClass != null) {
            startActivity(new Intent(UtilMainActivity.this, targetClass));
        }
    }
}
