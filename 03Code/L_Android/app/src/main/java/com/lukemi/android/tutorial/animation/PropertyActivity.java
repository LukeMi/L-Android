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
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyActivity extends AppCompatActivity {

    public static final String TAG = PropertyActivity.class.getSimpleName();

    @BindView(R.id.iv_switch)
    ImageView ivSwitch;
    @BindView(R.id.tv_area)
    TextView tvArea;

    @BindView(R.id.v_bottom)
    View mVBottom;

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
     * 测量Lo
     */
    private void measure() {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tvArea.measure(w, h);
        height = tvArea.getMeasuredHeight();
    }

    @OnClick({R.id.iv_switch, R.id.v_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch:
                anim();
                break;
            case R.id.v_bottom:
                start1();
                break;
            default:
                break;
        }
    }

    private void start1() {
        int defWidth = DensityUtil.dp2px(120);
        int defHeight = DensityUtil.dp2px(140);
        int maxWidth = getResources().getDisplayMetrics().widthPixels;
        int maxHeight = (int) (getResources().getDisplayMetrics().widthPixels * (((float) 140 / 120)));
        KLog.d(TAG, maxWidth + " : " + maxHeight + " ; " + (float) ((float) 140 / 120));
        boolean isMin = mVBottom.getWidth() != maxWidth;
        int start = isMin ? defWidth : maxWidth;
        int end = isMin ? maxWidth : defWidth;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(animation -> {
            ViewGroup.LayoutParams layoutParams = mVBottom.getLayoutParams();
            layoutParams.width = (int) animation.getAnimatedValue();
            layoutParams.height = (int) (layoutParams.width * ((float) 140 / 120));
            mVBottom.setLayoutParams(layoutParams);
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                KLog.d(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                KLog.d(TAG, "onAnimationEnd");
            }
        });
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setDuration(250);
        valueAnimator.setTarget(mVBottom);
        valueAnimator.start();
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
        valueAnimator.setDuration(250);
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
