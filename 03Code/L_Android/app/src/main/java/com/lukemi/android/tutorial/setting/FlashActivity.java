package com.lukemi.android.tutorial.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lukemi
 * @date 2018/12/24 18:45
 * @des 闪光灯
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class FlashActivity extends AppCompatActivity {
    @BindView(R.id.btn_switch)
    Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
    }

    @OnClick(R.id.btn_switch)
    public void onViewClicked() {

    }
}
