package com.lukemi.android.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.android.collection.animation.AnimationActivity;
import com.lukemi.android.collection.handler.HandlerTestActivity;
import com.lukemi.android.collection.setting.SettingActivity;
import com.lukemi.android.collection.webservice.WebServiceActivity;
import com.lukemi.android.collection.sessionlifecycle.SessionActivity1;
import com.lukemi.android.collection.utiltest.UtilMainActivity;
import com.lukemi.android.collection.widget.WidgetActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Class<?> targrtClass = null;
        switch (v.getId()) {
            case R.id.anim_main:
                targrtClass = AnimationActivity.class;
                break;
            case R.id.handler_main:
                targrtClass = HandlerTestActivity.class;
                break;
            case R.id.web_main:
                targrtClass = WebServiceActivity.class;
                break;
            case R.id.activityLifeCycle_main:
                targrtClass = SessionActivity1.class;
                break;
            case R.id.third_API:
                targrtClass = ThirdAPIActivity.class;
                break;
            case R.id.widgetActivity:
                targrtClass = WidgetActivity.class;
                break;
            case R.id.utilMainActivity:
                targrtClass = UtilMainActivity.class;
                break;
            case R.id.settingActivity:
                targrtClass = SettingActivity.class;
                break;
        }
        if (targrtClass != null) {
            startActivity(new Intent(MainActivity.this, targrtClass));
        }
    }
}
