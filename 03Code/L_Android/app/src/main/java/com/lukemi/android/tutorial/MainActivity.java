package com.lukemi.android.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.animation.AnimationActivity;
import com.lukemi.android.tutorial.category.ComponentActivity;
import com.lukemi.android.tutorial.setting.SettingActivity;
import com.lukemi.android.tutorial.utiltest.UtilMainActivity;
import com.lukemi.android.tutorial.widget.WidgetActivity;
import com.lukemi.android.tutorial.widget.wechat.activity.WeChatMainActivity;
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
        updateVersion(this);
    }

    private void updateVersion(MainActivity mainActivity) {
        //tomcate模拟版本更新
        final String verUrl = "http://127.0.0.1:8080/verupdate.txt";
        OkGo.get(verUrl)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logcat.log("updateVersion: " + s);
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
        Class<?> targetClass = null;
        switch (v.getId()) {
            case R.id.btn_anim:
                targetClass = AnimationActivity.class;
                break;
            case R.id.btn_component:
                targetClass = ComponentActivity.class;
                break;
            case R.id.third_API:
                targetClass = ThirdAPIActivity.class;
                break;
            case R.id.widgetActivity:
                targetClass = WidgetActivity.class;
                break;
            case R.id.utilMainActivity:
                targetClass = UtilMainActivity.class;
                break;
            case R.id.btn_intent:
                targetClass = SettingActivity.class;
                break;
            case R.id.btn_android:
                targetClass = AndroidActivity.class;
                break;
            case R.id.btn_emulate:
                targetClass = WeChatMainActivity.class;
                break;
            default:
                break;
        }
        if (targetClass != null) {
            startActivity(new Intent(MainActivity.this, targetClass));
        }
    }
}
