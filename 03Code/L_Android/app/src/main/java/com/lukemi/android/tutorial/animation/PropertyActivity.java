package com.lukemi.android.tutorial.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyActivity extends AppCompatActivity {

    @BindView(R.id.iv_switch)
    ImageView ivSwitch;
    @BindView(R.id.tv_area)
    TextView tvArea;

    private int height;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        ButterKnife.bind(this);
//        measure();
        Logcat.log("height : height " + height);
    }

    /**
     * 测量
     */
    private void measure() {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tvArea.measure(w, h);
        height = tvArea.getMeasuredHeight();
    }

    @OnClick(R.id.iv_switch)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch:
                anim();
                break;
            default:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        height = tvArea.getHeight();
        Logcat.log("height : height " + height + " ;" + ivSwitch.getWidth());
    }

    private void anim() {
        AnimatorSet animatorSet = new AnimatorSet();
        if (valueAnimator == null) {
            valueAnimator = new ValueAnimator();
        }
        if (valueAnimator.isRunning()) {
            return;
        }
        Logcat.log("height : " + height);
        /**
         * View的状态
         */
        boolean isCollapse = tvArea.getHeight() == 0;
        ivSwitch.setRotation(isCollapse ? 0 : 180);
        valueAnimator.setIntValues(isCollapse ? 0 : height, isCollapse ? height : 0);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.start();
        valueAnimator.addUpdateListener(animation -> {
            ViewGroup.LayoutParams layoutParams = tvArea.getLayoutParams();
            layoutParams.height = (int) animation.getAnimatedValue();
            tvArea.setLayoutParams(layoutParams);
        });
        animatorSet.play(valueAnimator);
        animatorSet.start();
    }
}
