package com.jeferry.android.widget;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.util.EditTextUtil;

/**
 * @author lukemi
 * @date 2019/2/14 10:46
 * @des ScrollView
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class ScrollViewActivity extends AppCompatActivity {

    EditText mEtReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        mEtReason = findViewById(R.id.et_reason);
        mEtReason.setOnTouchListener((v, event) -> {
            if (EditTextUtil.canScrollY(mEtReason)) {
                mEtReason.getParent().requestDisallowInterceptTouchEvent(true);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mEtReason.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            return false;
        });
    }
}
