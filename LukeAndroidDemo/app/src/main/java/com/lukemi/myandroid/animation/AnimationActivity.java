package com.lukemi.myandroid.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lukemi.myandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {

    }

    @OnClick({R.id.tween_Ani, R.id.fram_Ani, R.id.property_Ani})
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.tween_Ani:
                intent.setClass(this, TweenActivity.class);
                break;
            case R.id.fram_Ani:
                intent.setClass(this, FrameActivity.class);
                break;
            case R.id.property_Ani:
                intent.setClass(this, PropertyActivity.class);
                break;
        }
        startActivity(intent);
    }
}
