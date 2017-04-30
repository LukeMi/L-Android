package com.lukemi.myandroid.setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_setting);
        initView();
    }

    private void initView() {
        findViewById(com.lukemi.myandroid.R.id.wallPaperActivity).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.systemIntentActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?> targetClass = null;
        switch (v.getId()) {
            //手机壁纸功能
            case com.lukemi.myandroid.R.id.wallPaperActivity:
                targetClass = WallPaperActivity.class;
                break;
            //手机系统各种设置界面
            case com.lukemi.myandroid.R.id.systemIntentActivity:
                targetClass = SettingIntentActivity.class;
                break;
            default:
                break;
        }
        if (targetClass != null) {
            startActivity(new Intent(SettingActivity.this, targetClass));
        }
    }
}
