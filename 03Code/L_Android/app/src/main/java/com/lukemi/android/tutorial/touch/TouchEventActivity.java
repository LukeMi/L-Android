package com.lukemi.android.tutorial.touch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;

public class TouchEventActivity extends AppCompatActivity {

    public static final String TAG = TouchEventActivity.class.getSimpleName();

    ViewGroup clVg;

    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        initView();
    }

    /*
     @Override
     public boolean dispatchTouchEvent(MotionEvent ev) {
         KLog.d(TAG, "dispatchTouchEvent");
         return super.dispatchTouchEvent(ev);
     }

     @Override
     public boolean onTouchEvent(MotionEvent event) {
         KLog.d(TAG, "onTouchEvent");
         return super.onTouchEvent(event);
     }
 */
    private void initView() {
        clVg = findViewById(R.id.cl_vg);
        view = findViewById(R.id.view);

        clVg.setOnClickListener(this::onViewClicked);
        view.setOnClickListener(this::onViewClicked);
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
