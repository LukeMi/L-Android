package com.lukemi.android.tutorial.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends AppCompatActivity {


    @BindView(R.id.tv_title_type1)
    TextView tvTitleType1;

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
        tvTitleType1.setText("动画效果");
    }

    @OnClick({R.id.img_back, R.id.btn_tween, R.id.btn_fram, R.id.btn_property})
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_tween:
                intent.setClass(this, TweenActivity.class);
                break;
            case R.id.btn_fram:
                intent.setClass(this, FrameActivity.class);
                break;
            case R.id.btn_property:
                intent.setClass(this, PropertyActivity.class);
                break;
            default:
                break;
        }
        if (intent.getComponent() != null) {
            startActivity(intent);
        }
    }
}
