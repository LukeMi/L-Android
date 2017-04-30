package com.lukemi.myandroid.baidu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BDActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_bd);
        initView();
    }

    /**
     * 初始化控件
     * <p>
     * created by: tbug
     * created at: 2017/4/5 18:00
     */
    private void initView() {
        findViewById(com.lukemi.myandroid.R.id.bdLoc).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?> targetClass = null;
        switch (v.getId()) {
            case com.lukemi.myandroid.R.id.bdLoc:
                targetClass = BDLocActivity.class;
                break;
            default:
                break;
        }
        if (targetClass != null) {
            startActivity(new Intent(BDActivity.this, BDLocActivity.class));
        }
    }
}
