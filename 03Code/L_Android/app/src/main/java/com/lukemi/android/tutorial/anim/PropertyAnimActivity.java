package com.lukemi.android.tutorial.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;
import com.lukemi.android.common.util.Logcat;

import butterknife.BindView;
import butterknife.OnClick;

public class PropertyAnimActivity extends AbsBaseActivity {
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.img_bkg)
    ImageView imgBkg;

    @Override
    protected int bindLayout() {
        return R.layout.activity_property_anim;
    }

    @Override
    protected void initView() {
        for (int i = 0; i < llTop.getChildCount(); i++) {
            llTop.getChildAt(i).setOnClickListener(mOnClickListener);
        }
        for (int i = 0; i < llBottom.getChildCount(); i++) {
            llBottom.getChildAt(i).setOnClickListener(mOnClickListener);
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String msg = null;
            switch (view.getId()) {
                case R.id.tv_top_item_1:
                    msg = "tv_top_item_1";
                    break;
                case R.id.tv_top_item_2:
                    msg = "tv_top_item_2";
                    break;
                case R.id.tv_top_item_3:
                    msg = "tv_top_item_3";
                    break;
                case R.id.tv_bottom_item_1:
                    msg = "tv_bottom_item_1";
                    break;
                case R.id.tv_bottom_item_2:
                    msg = "tv_bottom_item_2";
                    break;
                case R.id.tv_bottom_item_3:
                    msg = "tv_bottom_item_3";
                    break;
            }
            Toast.makeText(PropertyAnimActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    private void animator() {
     /*   ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imgAnim, "translationX", 0f, 200f);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imgAnim, "translationY", 0f, 200f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgAnim, "rotation", 0f, 270f);
        AnimatorSet animatorSet = new AnimatorSet();
//      同时播放
//        animatorSet.playTogether(objectAnimatorX,objectAnimatorY,objectAnimator);
//      依次播放
//        animatorSet.playSequentially(objectAnimatorX,objectAnimatorY,objectAnimator);
//      控制顺序，先平移再旋转
//      水平方向上平移和竖直方向平移同时进行
        animatorSet.play(objectAnimatorX).with(objectAnimatorY);
//      平移完成之后再旋转
        animatorSet.play(objectAnimator).after(objectAnimatorX);
        animatorSet.setDuration(1000);
        animatorSet.start();*/
    }

    boolean isClose = false;

    @OnClick(R.id.img_bkg)
    public void onViewClicked() {
        isClose = !isClose;
      /*  if (isClose) {
            open();
        } else {
            close();
        }*/
        setCVisible(isClose);
    }


    private void setCVisible(boolean visible) {
        llTop.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        llBottom.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }


    private void open() {
        int height = llTop.getHeight();
        float y = llBottom.getY();
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        float density = getResources().getDisplayMetrics().density;
        Logcat.log("y : " + y + " ;height : " + height + " ;density : " + density);

        llTop.setVisibility(View.VISIBLE);
        llBottom.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimatorYllTop = ObjectAnimator.ofFloat(llTop, "translationY", -height, 0f);
        ObjectAnimator alphaLlTop = ObjectAnimator.ofFloat(llTop, "alpha", 0, 1f);
        float start = heightPixels;
        float end = heightPixels - height;
        ObjectAnimator objectAnimatorYllBottom = ObjectAnimator.ofFloat(llBottom, "translationY", 1, 0);
        Logcat.log("start : " + start + " ;end : " + end);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorYllTop)
                .with(alphaLlTop)
                .with(objectAnimatorYllBottom);

        animatorSet
                .setDuration(1000)
                .setInterpolator(new AccelerateInterpolator());
        animatorSet.addListener(mAnimatorListener);

        animatorSet.start();
    }

    private void close() {
        int height = llTop.getHeight();
        ObjectAnimator objectAnimatorYllTop = ObjectAnimator.ofFloat(llTop, "translationY", 0f, -height);

        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        float start = heightPixels;
        float end = heightPixels - height;

        ObjectAnimator objectAnimatorYllBottom = ObjectAnimator.ofFloat(llBottom, "translationY", end, start);
        ObjectAnimator alphaLlTop = ObjectAnimator.ofFloat(llTop, "alpha", 1f, 0);
        Logcat.log(/*"y : " + y + */" ;height : " + height + " ; heightPixels : " + heightPixels);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorYllTop)
                .with(alphaLlTop)
                .with(objectAnimatorYllBottom);
        animatorSet.addListener(mAnimatorListener);
        animatorSet
                .setDuration(1000)
                .setInterpolator(new AccelerateInterpolator());
        animatorSet.start();
    }


    private Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
            imgBkg.setEnabled(false);
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            float y = llBottom.getY();
            Logcat.log("y : " + y);
            imgBkg.setEnabled(true);
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };
}
