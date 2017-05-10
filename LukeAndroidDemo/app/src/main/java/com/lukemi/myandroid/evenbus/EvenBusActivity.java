package com.lukemi.myandroid.evenbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.myandroid.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * evenbus 先订阅 再post  onCreate（注册） onDestroy(取消注册) 声明处理方法
 */
public class EvenBusActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even_bus);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                EventBus.getDefault().post("第一条信息");
                EventBus.getDefault().post(2);
                EventBus.getDefault().post(true);
                EventBus.getDefault().post(3.1415f);
                EventBus.getDefault().post(3.1415926);
                finish();
                break;
        }
    }
}
