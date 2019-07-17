package com.lukemi.android.tutorial.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

/**
 * @author mzchen
 * @date 2019/7/17 17:39
 * @des RadioGroup 联系
 * @mail
 */
public class RadioGroupActivity extends AppCompatActivity {

    private RadioGroup mRg;
    private TextView mTvRg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group);
        initView();
    }

    private void initView() {
        mRg = findViewById(R.id.rg1);
        mTvRg1 = findViewById(R.id.tv_rg1);
        mRg.setOnCheckedChangeListener(this::onCheckedChanged);
        mTvRg1.setOnClickListener(view -> {
            for (int i = 0; i < mRg.getChildCount(); i++) {
                ((RadioButton) mRg.getChildAt(i)).setChecked(false);
            }
            mTvRg1.setText("");
        });
    }

    /**
     * RadioGroup check 事件
     *
     * @param group
     * @param checkedId
     */
    private void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.rg1:
                String text = "group.getId() : " + group.getId() + "\n\n" +
                        "checkedId : " + checkedId + "\n\n";
                mTvRg1.setText(text);
                break;
            default:
                break;
        }
    }
}
