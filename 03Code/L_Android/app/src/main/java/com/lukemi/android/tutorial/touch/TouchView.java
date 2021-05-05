package com.lukemi.android.tutorial.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.socks.library.KLog;

public class TouchView extends View {

    private static final String TAG = TouchView.class.getSimpleName();

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        KLog.d(TAG, "onAttachedToWindow");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int hSize = MeasureSpec.getSize(widthMeasureSpec);
        int vSize = MeasureSpec.getSize(heightMeasureSpec);
        KLog.d(TAG, "hSize : " + hSize + " ;vSize : " + vSize);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        KLog.d(TouchEventActivity.TAG, "dispatchTouchEvent");
//        boolean b = super.dispatchTouchEvent(event);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        KLog.d(TouchEventActivity.TAG, "b : " + b + " ;onTouchEvent ,action: " + event.getAction());
        return true;
    }
}
