package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.EditText;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.util.EditTextUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lukemi
 * @date 2019/2/14 10:46
 * @des ScrollView
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class ScrollViewActivity extends AppCompatActivity {

    @BindView(R.id.et_reason)
    EditText mEtReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        ButterKnife.bind(this);
        mEtReason.setOnTouchListener((v, event) -> {
            if (EditTextUtil.canScrollY(mEtReason)){
                mEtReason.getParent().requestDisallowInterceptTouchEvent(true);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    mEtReason.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            return false;
        });
    }
}
