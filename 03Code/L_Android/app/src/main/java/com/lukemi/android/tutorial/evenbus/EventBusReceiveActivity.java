package com.lukemi.android.tutorial.evenbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.evenbus.event.NumberEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lukemi
 * @date 2018/12/24 15:33
 * @des EventBus 使用 ，注意注册，与反注册 ，无法传递 基本数据类型，只能传递对象
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class EventBusReceiveActivity extends AppCompatActivity {

    @BindView(R.id.text_event1)
    TextView textEvent1;
    @BindView(R.id.text_event2)
    TextView textEvent2;
    @BindView(R.id.text_event3)
    TextView textEvent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_receive);
        ButterKnife.bind(this);
        // 在要接收消息的页面的OnCreate()中注册EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在OnDestroy()方法中解注册EventBus
        EventBus.getDefault().unregister(this);
    }

    //处理操作开始
    @Subscribe(threadMode = ThreadMode.MAIN)//主线程
    public void onEvenBusMain1(String event) {
        textEvent1.setText(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)//主线程
    public void onEvenBusMain2(NumberEvent event) {
        textEvent2.setText("消息结果为: " + event.toString());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)//主线程
    public void onEvenBusMain3(boolean event) {
        textEvent3.setText("消息结果为: " + event);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)//主线程
    public void onEvenBusMain4(float event) {
        Logcat.log("ThreadMode.BACKGROUND -->> 消息结果为: " + event);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)//主线程    申明订阅后的线程操作
    public void onEvenBusMain5(double event) {
        Logcat.log("ThreadMode.ASYNC -->> 消息结果为: " + event);
    }
    //处理操作结束

    @OnClick({R.id.btn_test})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                startActivity(new Intent(this, EvenBusSendActivity.class));
                break;
            default:
                break;
        }
    }
}
