package com.lukemi.android.common.test;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.lukemi.android.common.R;
import com.lukemi.android.common.view.CirculaArcView;


public class TestActivity extends AppCompatActivity {

    CirculaArcView circulaArcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void onViewClicked() {
     /*   Toast.makeText(this, "circulaArcView", Toast.LENGTH_SHORT).show();
        ScaleAnimation animation = new ScaleAnimation(1, 3, 1, 3, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
       animation.setInterpolator(new AccelerateDecelerateInterpolator());
        circulaArcView.startAnimation(animation);*/

        final ValueAnimator animator = ValueAnimator.ofInt(1, 100);
        animator.setDuration(1200);
        animator.setInterpolator(new AccelerateInterpolator());//线性效果变化
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Integer integer = (Integer) animator.getAnimatedValue();
                circulaArcView.setCa_radio(integer);
                circulaArcView.setAlpha(255 - integer);
            }
        });
        //动画监听
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                circulaArcView.setCa_radio(50);
                Toast.makeText(TestActivity.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
