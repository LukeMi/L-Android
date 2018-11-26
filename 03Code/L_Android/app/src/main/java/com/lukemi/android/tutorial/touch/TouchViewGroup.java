package com.lukemi.android.tutorial.touch;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lukemi.android.common.util.Logcat;

public class TouchViewGroup extends ConstraintLayout {


    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        Logcat.log("TouchViewGroup onInterceptTouchEvent  b : " + b);
        return b;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Logcat.log("TouchViewGroup dispatchTouchEvent  b : " + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Logcat.log("TouchViewGroup onTouchEvent  b : " + b);
        return b;
    }
}
