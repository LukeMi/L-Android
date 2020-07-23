package com.lukemi.android.tutorial.touch;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.socks.library.KLog;

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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        KLog.d(TouchEventActivity.TAG, "onInterceptTouchEvent : " + b);
        return b;
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        KLog.d(TouchEventActivity.TAG, "onInterceptTouchEvent");
        boolean b = super.onInterceptTouchEvent(ev);
        KLog.d(TouchEventActivity.TAG, "onInterceptTouchEvent : " + b);
        return b;
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        KLog.d(TouchEventActivity.TAG, "onTouchEvent");
        boolean b = super.onTouchEvent(event);
        KLog.d(TouchEventActivity.TAG, "onTouchEvent : " + b + " action : " + event.getAction());
        return b;
//        return false;
    }
}
