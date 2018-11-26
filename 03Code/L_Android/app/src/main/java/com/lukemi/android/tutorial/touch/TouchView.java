package com.lukemi.android.tutorial.touch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lukemi.android.common.util.Logcat;

public class TouchView extends View {
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
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean b = super.dispatchTouchEvent(event);
        Logcat.log("TouchView dispatchTouchEvent  b : " + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Logcat.log("TouchView onTouchEvent  b : " + b);
        return b;
    }
}
