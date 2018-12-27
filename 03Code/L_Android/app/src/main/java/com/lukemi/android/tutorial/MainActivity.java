package com.lukemi.android.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.animation.AnimationActivity;
import com.lukemi.android.tutorial.category.ComponentActivity;
import com.lukemi.android.tutorial.manager.ManagerStatisticsActivity;
import com.lukemi.android.tutorial.setting.SettingActivity;
import com.lukemi.android.tutorial.utiltest.UtilMainActivity;
import com.lukemi.android.tutorial.widget.IntentJumpAdapter;
import com.lukemi.android.tutorial.widget.IntentJumpBean;
import com.lukemi.android.tutorial.widget.WidgetActivity;
import com.lukemi.android.tutorial.widget.wechat.activity.WeChatMainActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;
    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = (BaseQuickAdapter adapter, View view, int position) -> {
        Class<?> c = ((IntentJumpBean) adapter.getData().get(position)).getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
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
                            //成功
                            if (object.optString("rsm").equals("1")) {
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

    private void initData() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("四大组件", ComponentActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("动画", AnimationActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("API", ThirdAPIActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Widget", WidgetActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Util", UtilMainActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("system", SettingActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("android", AndroidActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Emulate Third App", WeChatMainActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("ManagerStatistics", ManagerStatisticsActivity.class));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}
