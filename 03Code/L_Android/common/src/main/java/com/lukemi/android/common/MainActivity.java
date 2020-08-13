package com.lukemi.android.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lukemi.android.common.base.RoutePath;
import com.lukemi.android.common.test.DiDiViewActivity;
import com.lukemi.android.common.test.FloatViewActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_common);
        initView();
    }

    private void initView() {
        findViewById(R.id.fontActivity).setOnClickListener(this::onClick);
        findViewById(R.id.diDiViewActivity).setOnClickListener(this::onClick);
        findViewById(R.id.floatViewActivity).setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        Intent intent = null;
        int id = view.getId();
        if (id == R.id.diDiViewActivity) {
            intent = new Intent(this, DiDiViewActivity.class);
        } else if (id == R.id.floatViewActivity) {
            intent = new Intent(this, FloatViewActivity.class);
        } else if (id == R.id.fontActivity) {
            ARouter
                    .getInstance()
                    .build(RoutePath.FONT_ACTIVITY)
                    .navigation();
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
