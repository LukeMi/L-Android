package com.lukemi.android.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

import com.lukemi.android.common.R;
import com.lukemi.android.common.util.Logcat;

/**
 * QQ空间头部可向下拉伸空间
 * Created by tbug on 2017/3/20.
 */
public class ZoomHeadListView extends ListView {

    private ImageView imageView;
    private int defaultImageViewHeight;

    public ZoomHeadListView(Context context) {
        this(context, null);
    }

    public ZoomHeadListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomHeadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defaultImageViewHeight = context.getResources().getDimensionPixelSize(R.dimen.defaultImageViewHeight);
    }

    public void setZoomImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    //过度拉伸解决的问题
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (deltaY < 0) {//向下过度拉伸
            imageView.getLayoutParams().height = imageView.getHeight() - deltaY;
            imageView.requestLayout();
        } else {//向上拉升
            if (imageView.getHeight() > defaultImageViewHeight) {
                imageView.getLayoutParams().height = imageView.getHeight() - deltaY;
                imageView.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        Logcat.log("onScrollChanged --> int l= " + l + ", int t= " + t + ", int oldl= " + oldl + ", int oldt= " + oldt + "");
        View parent = (View) imageView.getParent();
        int deltaY = parent.getTop();
        Logcat.log("onScrollChanged --> deltaY= " + deltaY);
        if (deltaY < 0 && imageView.getHeight() > defaultImageViewHeight) {
            imageView.getLayoutParams().height = imageView.getHeight() + deltaY;
            parent.layout(parent.getLeft(), 0, parent.getRight(), parent.getHeight());
            imageView.requestLayout();
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                //执行动画
                ResetAnimation resetAnimation = new ResetAnimation(imageView, defaultImageViewHeight);
                resetAnimation.setDuration(300);
                imageView.startAnimation(resetAnimation);
                break;
        }
        return super.onTouchEvent(ev);
    }

    class ResetAnimation extends Animation {
        private int originalHeight;//原始高度
        private int currentHeight;//现在的高度
        private int gapHeight;//高度差

        public ResetAnimation() {
            super();
        }

        public ResetAnimation(ImageView iv, int originalHeight) {
            this.originalHeight = originalHeight;
            currentHeight = iv.getHeight();
            gapHeight = currentHeight - originalHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            imageView.getLayoutParams().height = (int) (currentHeight - (gapHeight * interpolatedTime));
            imageView.requestLayout();
            super.applyTransformation(interpolatedTime, t);
        }
    }
}
