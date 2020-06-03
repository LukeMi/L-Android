package com.jeferry.android.widget.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.socks.library.KLog;

public class EventDistributeActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private EventLogViewGroup mViewGroup;

    private EventLogView view;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
//        boolean result = true;
        KLog.i(TAG, "dispatchTouchEvent : " + result + " ; MotionEvent : " + getDesc(ev.getAction()));
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
//        boolean result = true;
        KLog.i(TAG, "onTouchEvent : " + result + " ; MotionEvent : " + getDesc(event.getAction()));
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.common.R.layout.activity_event_distribute);
        mViewGroup = findViewById(com.lukemi.android.common.R.id.viewGroup);
//        view = findViewById(com.lukemi.android.common.R.id.view);

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