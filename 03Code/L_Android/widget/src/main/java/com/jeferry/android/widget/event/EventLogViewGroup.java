package com.jeferry.android.widget.event;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.socks.library.KLog;

class EventLogViewGroup extends FrameLayout {

    public final String TAG = this.getClass().getSimpleName();

    public EventLogViewGroup(@NonNull Context context) {
        super(context);
    }

    public EventLogViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventLogViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EventLogViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
//        boolean result = true;
        KLog.i(TAG, "dispatchTouchEvent : " + result + " ; MotionEvent : " + getDesc(event.getAction()));
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean result = super.onInterceptTouchEvent(ev);
        boolean result = true;
        KLog.i(TAG, "onInterceptTouchEvent : " + result + " ; MotionEvent : " + getDesc(ev.getAction()));
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        boolean result = super.onTouchEvent(event);
        boolean result = true;
        KLog.i(TAG, "onTouchEvent : " + result + " ; MotionEvent : " + getDesc(event.getAction()));
        return result;
    }

    private String getDesc(int action) {
        String desc = "null";
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                desc = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                desc = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                desc = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                desc = "ACTION_CANCEL";
                break;
            default:
                desc = "default";
                break;
        }
        return desc;
    }
}
