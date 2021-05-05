package com.lukemi.android.tutorial.touch;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

public class TouchEventActivity extends AppCompatActivity {

    public static final String TAG = TouchEventActivity.class.getSimpleName();

    TouchViewGroup clVg;

    TouchView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        initView();
        KLog.d(TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        KLog.d(TAG, "onResume : " + view.getMeasuredWidth() + " ; " + view.getWidth());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        KLog.d(TAG, "onWindowFocusChanged : " + view.getMeasuredWidth() + " ; " + view.getWidth());
    }

    /*     @Override
     public boolean dispatchTouchEvent(MotionEvent ev) {
         KLog.d(TAG, "dispatchTouchEvent");
         return super.dispatchTouchEvent(ev);
     }

     @Override
     public boolean onTouchEvent(MotionEvent event) {
         KLog.d(TAG, "onTouchEvent");
         return super.onTouchEvent(event);
     }*/

    private void initView() {
        clVg = findViewById(R.id.cl_vg);
        view = findViewById(R.id.view);

        clVg.setOnClickListener(this::onViewClicked);
        view.setOnClickListener(this::onViewClicked);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                KLog.d(TAG, "measuredWidth : " + measuredWidth + " ;measuredHeight : " + measuredHeight);
            }
        });

    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view:
                Toast.makeText(this, "view click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cl_vg:
                Toast.makeText(this, "viewGroup click", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
