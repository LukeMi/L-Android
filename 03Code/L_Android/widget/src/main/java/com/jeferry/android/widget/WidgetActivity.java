package com.jeferry.android.widget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jeferry.android.widget.bottomsheet.BottomSheetActivity;
import com.jeferry.android.widget.event.EventDistributeActivity;
import com.jeferry.android.widget.flexbox.FlexBoxLayoutActivity;
import com.jeferry.android.widget.fragment.FragmentActivity;
import com.jeferry.android.widget.notification.NotificationActivity;
import com.jeferry.android.widget.recycle.flexbox.RecyclerViewFlexBoxActivity;
import com.jeferry.android.widget.recycle.itemdecoration.RecyclerViewActivity;
import com.jeferry.android.widget.room.StudentActivity;
import com.jeferry.android.widget.wechat.activity.WeChatMainActivity;
import com.jeferry.android.widget.wechat.tablayout.TabLayoutActivity;
import com.lukemi.android.common.IntentJumpAdapter;
import com.lukemi.android.common.IntentJumpBean;
import com.lukemi.android.common.config.ARouterPath;

import java.util.ArrayList;
import java.util.List;


@Route(path = ARouterPath.GROUP_WIDGET + ARouterPath.MAIN)
public class WidgetActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<IntentJumpBean> bindBeanList = new ArrayList<>();
    private IntentJumpAdapter intentJumpAdapter;
    private OnItemClickListener mOnItemClickListener = (adapter, view, position) -> {
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
        initData();
        initView();
    }

    private void initData() {
        bindBeanList.add(new IntentJumpBean("自定义View测试", CustomViewListActivity.class));
        bindBeanList.add(new IntentJumpBean("ConstraintLayout", ConstraintLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("Constraint1Layout", ConstraintLayout1Activity.class));
        bindBeanList.add(new IntentJumpBean("TextView", TextViewActivity.class));
        bindBeanList.add(new IntentJumpBean("EditText", EditTextActivity.class));
        bindBeanList.add(new IntentJumpBean("EditText1", EditTextInputMethodAutoActivity.class));
        bindBeanList.add(new IntentJumpBean("Button", OnClickActivity.class));
        bindBeanList.add(new IntentJumpBean("ImageButton", ImageViewButtonActivity.class));
        bindBeanList.add(new IntentJumpBean("CheckBox", null));
        bindBeanList.add(new IntentJumpBean("RadioGroup", RadioGroupActivity.class));
        bindBeanList.add(new IntentJumpBean("Toast", ToastActivity.class));
        bindBeanList.add(new IntentJumpBean("Spinner", null));
        bindBeanList.add(new IntentJumpBean("ListView", ListViewActivity.class));
        bindBeanList.add(new IntentJumpBean("RecyclerView", RecyclerViewActivity.class));
        bindBeanList.add(new IntentJumpBean("RecyclerViewFlexBoxLayout", RecyclerViewFlexBoxActivity.class));
        bindBeanList.add(new IntentJumpBean("TabHost", null));
        bindBeanList.add(new IntentJumpBean("Menu", MenuActivity.class));
        bindBeanList.add(new IntentJumpBean("AutoCompleteTextView", AutoCompleteActivity.class));
        bindBeanList.add(new IntentJumpBean("TabLayout", TabLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("ViewFlipper", ViewFlipperActivity.class));
        bindBeanList.add(new IntentJumpBean("ImageView", ImageViewActivity.class));
        bindBeanList.add(new IntentJumpBean("Gallery", null));
        bindBeanList.add(new IntentJumpBean("ImageSwitcher", null));
        bindBeanList.add(new IntentJumpBean("GridView", null));
        bindBeanList.add(new IntentJumpBean("ProgressBar", ProgressBarActivity.class));
        bindBeanList.add(new IntentJumpBean("PopupWindow", PopupActivity.class));
        bindBeanList.add(new IntentJumpBean("SeekBar", SeekBarActivity.class));
        bindBeanList.add(new IntentJumpBean("RatingBar", RatingBarActivity.class));
        bindBeanList.add(new IntentJumpBean("Notification", NotificationActivity.class));
        bindBeanList.add(new IntentJumpBean("Webview", WebViewActivity.class));
        bindBeanList.add(new IntentJumpBean("ScrollView", ScrollViewActivity.class));
        bindBeanList.add(new IntentJumpBean("TableLayoutActivity", TableLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("WebviewDownloadActivity", WebviewDownloadActivity.class));
        bindBeanList.add(new IntentJumpBean("Merge标签使用", MergeActivity.class));
        bindBeanList.add(new IntentJumpBean("ViewStub标签使用", ViewStubActivity.class));
        bindBeanList.add(new IntentJumpBean("Include标签使用", IncludeActivity.class));
        bindBeanList.add(new IntentJumpBean("CollapsingToolbarLayout", CollapsingToolbarLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("WeChatMainActivity", WeChatMainActivity.class));
        bindBeanList.add(new IntentJumpBean("FlexBoxLayout", FlexBoxLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("CoordinatorLayout", CoordinatorLayoutActivity.class));
        bindBeanList.add(new IntentJumpBean("BottomSheetActivity", BottomSheetActivity.class));
        bindBeanList.add(new IntentJumpBean("Dialog", DialogActivity.class));
        bindBeanList.add(new IntentJumpBean("FragmentActivity", FragmentActivity.class));
        bindBeanList.add(new IntentJumpBean("FlowLayout", FlowActivity.class));
        bindBeanList.add(new IntentJumpBean("Switch", SwitchActivity.class));
        bindBeanList.add(new IntentJumpBean("CardView", CardViewActivity.class));
        bindBeanList.add(new IntentJumpBean("ViewPager", ViewPagerActivity.class));
        bindBeanList.add(new IntentJumpBean("CViewActivity", CViewActivity.class));
        bindBeanList.add(new IntentJumpBean("RadioGroup", RadioGroupMainActivity.class));
        bindBeanList.add(new IntentJumpBean("事件分发", EventDistributeActivity.class));
        bindBeanList.add(new IntentJumpBean("StudentActivity", StudentActivity.class));

        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, bindBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_intent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(intentJumpAdapter);
    }
}
