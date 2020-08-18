package com.lukemi.android.tutorial.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

public class AnimActivity extends AppCompatActivity {

    private TextView tvTitleType1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        initView();
    }

    protected void initView() {
        tvTitleType1 = findViewById(R.id.tv_title_type1);
        tvTitleType1.setText("动画练习");
        findViewById(R.id.img_back).setOnClickListener(this::onClick);
        findViewById(R.id.btn_tween).setOnClickListener(this::onClick);
        findViewById(R.id.btn_fram).setOnClickListener(this::onClick);
        findViewById(R.id.btn_property).setOnClickListener(this::onClick);
        findViewById(R.id.btn_90).setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_tween:
                intent = new Intent(this, TweenAnimActivity.class);
                break;
            case R.id.btn_fram:
                intent = new Intent(this, FrameAnimActivity.class);
                break;
            case R.id.btn_property:
                intent = new Intent(this, PropertyAnimActivity.class);
                break;
            case R.id.btn_90:
                intent = new Intent(this, Angle90RecycleActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
