package com.lukemi.myandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lukemi.myandroid.animation.AnimationActivity;
import com.lukemi.myandroid.handler.HandlerTestActivity;
import com.lukemi.myandroid.setting.SettingActivity;
import com.lukemi.myandroid.util.HttpUtils;
import com.lukemi.myandroid.util.Logcat;
import com.lukemi.myandroid.webservice.WebServiceActivity;
import com.lukemi.myandroid.sessionlifecycle.SessionActivity1;
import com.lukemi.myandroid.utiltest.UtilMainActivity;
import com.lukemi.myandroid.widget.WidgetActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        verUpdate(this);
    }

    private void verUpdate(MainActivity mainActivity) {
        //tomcate模拟版本更新
        final String verUrl = "http://127.0.0.1:8080/verupdate.txt";
        OkGo.get(verUrl)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logcat.log("verUpdate: " + s);
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.optString("rsm").equals("1")) {//成功
                                JSONObject data = object.getJSONObject("data");
                                String apkUrl = data.getString("url");

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }
                });
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
