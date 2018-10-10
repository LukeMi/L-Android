package com.lukemi.android.tutorial.anim;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TweenAnimActivity extends BaseActivity {

    @BindView(R.id.tv_title_type1)
    TextView tvTitleType1;
    @BindView(R.id.img_anim)
    ImageView imgAnim;
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.btn_alpha)
    Button btnAlpha;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_alpha_Scale)
    Button btnAlphaScale;
    @BindView(R.id.btn_translate_Alpha)
    Button btnTranslateAlpha;
    private TranslateAnimation translateAnimation;
    private RotateAnimation rotateAnimation;
    private ScaleAnimation scaleAnimation;
    private AlphaAnimation alphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_tween_anim;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_PARENT, -1f
        );
        translateAnimation.setDuration(3000);
        translateAnimation.setAnimationListener(mAnimationListener);
        translateAnimation.setInterpolator(new AccelerateInterpolator());

        scaleAnimation = new ScaleAnimation(
                1, 1.5f,
                1, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1
        );
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(mAnimationListener);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());

        alphaAnimation = new AlphaAnimation(
                0, 1
        );
        alphaAnimation.setDuration(3000);
        alphaAnimation.setAnimationListener(mAnimationListener);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());

        rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1
        );
        rotateAnimation.setDuration(3000);
        rotateAnimation.setAnimationListener(mAnimationListener);
        rotateAnimation.setInterpolator(new AccelerateInterpolator());

    }

    @Override
    protected void initView() {
        tvTitleType1.setText("补间动画");
    }

    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            setViewEnable(false);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            setViewEnable(true);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    private void setViewEnable(boolean enable) {
        btnTranslate.setEnabled(enable);
        btnAlpha.setEnabled(enable);
        btnRotate.setEnabled(enable);
        btnScale.setEnabled(enable);
        btnAlphaScale.setEnabled(enable);
        btnTranslateAlpha.setEnabled(enable);
    }

    @OnClick({R.id.img_back, R.id.btn_translate, R.id.btn_alpha,
            R.id.btn_rotate, R.id.btn_scale, R.id.btn_alpha_Scale
            , R.id.btn_translate_Alpha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_translate:
                translate();
                break;
            case R.id.btn_alpha:
                alpha();
                break;
            case R.id.btn_rotate:
                rotate();
                break;
            case R.id.btn_scale:
                alpha_Scale();
                break;
            case R.id.btn_alpha_Scale:
                scale();
                break;
            case R.id.btn_translate_Alpha:
                translate_Alpha();
                break;
        }
    }

    /**
     * 平移
     */
    private void translate() {

        imgAnim.startAnimation(translateAnimation);
    }

    /**
     * 缩放
     */
    private void scale() {

        imgAnim.startAnimation(scaleAnimation);
    }

    /**
     * 渐变
     */
    private void alpha() {

        imgAnim.startAnimation(alphaAnimation);
    }

    /**
     * 旋转
     */
    private void rotate() {

        imgAnim.startAnimation(rotateAnimation);
    }

    /**
     * 渐变 + 旋转
     */
    private void alpha_Scale() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        imgAnim.startAnimation(animationSet);
    }

    /**
     * 渐变 + 平移
     */
    private void translate_Alpha() {
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        imgAnim.startAnimation(animationSet);
    }

}
