package com.lukemi.android.tutorial.state;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <selector xmlns:android="http://schemas.android.com/apk/res/android"
 * android:constantSize=["true" | "false"]
 * android:dither=["true" | "false"]
 * android:variablePadding=["true" | "false"] >
 * <item
 * android:drawable="@[package:]drawable/drawable_resource"
 * android:state_pressed=["true" | "false"]
 * android:state_focused=["true" | "false"]
 * android:state_hovered=["true" | "false"]
 * android:state_selected=["true" | "false"]
 * android:state_checkable=["true" | "false"]
 * android:state_checked=["true" | "false"]
 * android:state_enabled=["true" | "false"]
 * android:state_activated=["true" | "false"]
 * android:state_window_focused=["true" | "false"] />
 * </selector>
 */
public class StateActivity extends AbsBaseActivity {

    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.tv_enable)
    TextView tvEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_state;
    }

    boolean selected = false;

    @OnClick({R.id.tv_press, R.id.tv_select, R.id.tv_enable})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_press:
                break;
            case R.id.tv_select:
                selected = !selected;
                tvSelect.setSelected(selected);
                tvEnable.setEnabled(selected);
                break;
            case R.id.tv_enable:
                break;
        }
    }
}
