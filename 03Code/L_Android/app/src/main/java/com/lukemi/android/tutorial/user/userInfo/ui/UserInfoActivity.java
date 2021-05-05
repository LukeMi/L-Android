package com.lukemi.android.tutorial.user.userInfo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;
import com.lukemi.android.tutorial.user.userInfo.adapter.UserInfoAdapter;
import com.lukemi.android.tutorial.user.userInfo.bean.UserInfoBean;
import com.lukemi.android.tutorial.user.userInfo.bean.UserParamsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;


public class UserInfoActivity extends AbsBaseActivity {

//    @BindView(R.id.list)
    RecyclerView list;
//    @BindView(R.id.tv_title)
    TextView tvTitle;
    private UserInfoAdapter adapter;
//    private ViewDataBinding viewDataBinding;
    private UserInfoBean userInfoBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void dateBind() {
//        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
//        ButterKnife.bind(this);
    }

    @Override
    protected void getIntentData() {
        userInfoBean = getIntent().getParcelableExtra("userBean");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        UserInfoBean userInfoBean = new UserInfoBean("张三", 0, 42, "13856976635", "学士");

//        viewDataBinding.setUser(userInfoBean);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new UserInfoAdapter(getList(userInfoBean), this);
        list.setAdapter(adapter);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_user_info;
    }

    private List<UserParamsBean> getList(UserInfoBean userInfoBean) {
        List<UserParamsBean> list = new ArrayList<>();
        list.add(new UserParamsBean("姓名：", userInfoBean.getName()));
        list.add(new UserParamsBean("性别：", userInfoBean.getGender() == 0 ? "女" : "男", UserParamsBean.ITEM_TYPE_2));
        list.add(new UserParamsBean("年龄：", userInfoBean.getAge() + "岁"));
        list.add(new UserParamsBean("手机号：", userInfoBean.getMobile(), UserParamsBean.ITEM_TYPE_3));
        list.add(new UserParamsBean("学历：", userInfoBean.getDegree()));
        return list;
    }


    @OnClick(R.id.tv_title)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                refreshName();
                break;
        }
    }

    boolean isZhangSan = true;

    private void refreshName() {
        isZhangSan = !isZhangSan;
        String name = isZhangSan ? "张三" : "李四 ";
        String title = name + "的简历";
        tvTitle.setText(title);
        List<UserParamsBean> data = adapter.getData();
        UserParamsBean userParamsBean = data.get(0);
        userParamsBean.setValue(name);
        adapter.notifyItemChanged(0);
    }
}
