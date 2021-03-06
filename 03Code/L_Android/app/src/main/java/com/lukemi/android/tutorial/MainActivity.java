package com.lukemi.android.tutorial;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lukemi.android.common.IntentJumpAdapter;
import com.lukemi.android.common.IntentJumpBean;
import com.lukemi.android.common.config.ARouterPath;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.animation.AnimationActivity;
import com.lukemi.android.tutorial.api.AndroidApiActivity;
import com.lukemi.android.tutorial.category.ComponentActivity;
import com.lukemi.android.tutorial.lib.FrameworkLibActivity;
import com.lukemi.android.tutorial.manager.ManagerStatisticsActivity;
import com.lukemi.android.tutorial.rxJava.RxJavaActivity;
import com.lukemi.android.tutorial.statics.StaticFieldActivity;
import com.lukemi.android.tutorial.system.SystemActivity;
import com.lukemi.android.tutorial.utiltest.UtilMainActivity;
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

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;
    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private OnItemClickListener mOnItemClickListener = (  adapter,   view,   position) -> {
        IntentJumpBean bean = (IntentJumpBean) adapter.getData().get(position);
        Class<?> c = bean.getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        } else if (null != bean.getPostcard()) {
            bean.getPostcard().navigation();
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
        Postcard widget = ARouter.getInstance().build(ARouterPath.GROUP_WIDGET + ARouterPath.MAIN);

        intentJumpBeanList.add(new IntentJumpBean("Widget", widget, null));
        intentJumpBeanList.add(new IntentJumpBean("Util", UtilMainActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("System", SystemActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("android", AndroidActivity.class));

        intentJumpBeanList.add(new IntentJumpBean("ManagerStatistics", ManagerStatisticsActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Static Field Test", StaticFieldActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Android Api", AndroidApiActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("RoundImageViewListActivity", RoundImageViewListActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("FrameworkLib", FrameworkLibActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("RxJavaActivity", RxJavaActivity.class));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}
