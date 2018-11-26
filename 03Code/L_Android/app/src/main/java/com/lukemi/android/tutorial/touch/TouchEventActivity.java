package com.lukemi.android.tutorial.touch;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;
import com.lukemi.android.common.util.Logcat;

import butterknife.BindView;
import butterknife.OnClick;

public class  TouchEventActivity extends BaseActivity {

    @BindView(R.id.view)
    View view;
    @BindView(R.id.cl_vg)
    ViewGroup clVg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Logcat.log("TouchActivity dispatchTouchEvent b : " + b);
        return b;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_touch_event;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.view, R.id.cl_vg})
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
