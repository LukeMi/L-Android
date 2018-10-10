package com.lukemi.android.tutorial.utiltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.android.tutorial.R;

public class UtilMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        findViewById(R.id.BitmapUtilActivity).setOnClickListener(this);
        findViewById(R.id.DeviceutilActivity).setOnClickListener(this);
        findViewById(R.id.ZipUtilActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?> targetClass = null;
        switch (v.getId()) {

            case R.id.BitmapUtilActivity:
                targetClass = BitmapUtilActivity.class;
                break;

            case R.id.DeviceutilActivity:
                targetClass = DeviceutilActivity.class;
                break;

            case   R.id.ZipUtilActivity:
                targetClass = ZipUtilActivity.class;
                break;
        }
        if (targetClass != null) {
            startActivity(new Intent(UtilMainActivity.this, targetClass));
        }
    }
}
