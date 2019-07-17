package com.lukemi.android.tutorial.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.notification.NotificationActivity;
import com.lukemi.android.tutorial.popup.PopupActivity;
import com.lukemi.android.tutorial.viewtest.DialogUtilActivity;
import com.lukemi.android.tutorial.widget.wechat.tablayout.TabLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WidgetActivity extends AppCompatActivity {

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;
    private List<IntentJumpBean> bindBeanList = new ArrayList<>();
    private IntentJumpAdapter intentJumpAdapter;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = (BaseQuickAdapter adapter, View view, int position) -> {
        IntentJumpBean o = (IntentJumpBean) adapter.getData().get(position);
        Class<?> c = o.getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        } else {
            Toast.makeText(getApplicationContext(), o.getText() + "暂未添加", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        bindBeanList.add(new IntentJumpBean("自定义View测试", CustomViewListActivity.class));
        bindBeanList.add(new IntentJumpBean("ConstraintLayout", ConstraintLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("TextView", TextViewActivity.class));
        bindBeanList.add(new IntentJumpBean("EditText", EditTextActivity.class));
        bindBeanList.add(new IntentJumpBean("Button", null));
        bindBeanList.add(new IntentJumpBean("ImageButton", ImageViewActivity.class));
        bindBeanList.add(new IntentJumpBean("CheckBox", null));
        bindBeanList.add(new IntentJumpBean("RadioGroup", null));
        bindBeanList.add(new IntentJumpBean("Toast", null));
        bindBeanList.add(new IntentJumpBean("Spinner", null));
        bindBeanList.add(new IntentJumpBean("ListView", ListViewActivity.class));
        bindBeanList.add(new IntentJumpBean("TabHost", null));
        bindBeanList.add(new IntentJumpBean("Menu", null));
        bindBeanList.add(new IntentJumpBean("AutoCompleteTextView", null));
        bindBeanList.add(new IntentJumpBean("TabLayout", TabLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("ViewFlipper", ViewFlipperActivity.class));
        bindBeanList.add(new IntentJumpBean("Dialog", DialogUtilActivity.class));
        bindBeanList.add(new IntentJumpBean("ImageView", null));
        bindBeanList.add(new IntentJumpBean("Gallery", null));
        bindBeanList.add(new IntentJumpBean("ImageSwitcher", null));
        bindBeanList.add(new IntentJumpBean("GridView", null));
        bindBeanList.add(new IntentJumpBean("ProgressBar", null));
        bindBeanList.add(new IntentJumpBean("PopupWindow", PopupActivity.class));
        bindBeanList.add(new IntentJumpBean("SeekBar", null));
        bindBeanList.add(new IntentJumpBean("RatingBar", null));
        bindBeanList.add(new IntentJumpBean("ProgressDialog", null));
        bindBeanList.add(new IntentJumpBean("Notification", NotificationActivity.class));
        bindBeanList.add(new IntentJumpBean("Webview", WebViewActivity.class));
        bindBeanList.add(new IntentJumpBean("ScrollView", ScrollViewActivity.class));
        bindBeanList.add(new IntentJumpBean("TableLayoutActivity", TableLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("WebviewDownloadActivity", WebviewDownloadActivity.class));
        bindBeanList.add(new IntentJumpBean("Merge标签使用", MergeActivity.class));
        bindBeanList.add(new IntentJumpBean("ViewStub标签使用", ViewStubActivity.class));
        bindBeanList.add(new IntentJumpBean("Include标签使用", IncludeActivity.class));
        bindBeanList.add(new IntentJumpBean("CollapsingToolbarLayout", CollapsingToolbarLayoutActivity.class));

        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, bindBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }


}
