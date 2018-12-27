package com.lukemi.android.tutorial.anim;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FrameAnimActivity extends AbsBaseActivity {

    @BindView(R.id.tv_title_type1)
    TextView tvTitleType1;
    @BindView(R.id.img_anim)
    ImageView imgAnim;
    private AnimationDrawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_frame_anim;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvTitleType1.setText("帧动画");
    }

    @Override
    protected void initView() {
        imgAnim.setImageResource(R.drawable.frame_anima_gj);
        mDrawable = (AnimationDrawable) imgAnim.getDrawable();
    }

    private void frameAnimation() {
        startFrame = !startFrame;
        if (startFrame) {
            mDrawable.start();
        } else {
            if (mDrawable != null) {
                mDrawable.stop();
            }
        }

    }

    private boolean startFrame = false;

    @OnClick({R.id.img_back, R.id.fab_op})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.fab_op:
                frameAnimation();
                break;
        }
    }
}
