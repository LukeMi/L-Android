package com.lukemi.android.tutorial.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.widget.wechat.fragment.WeChatContactFragment;
import com.lukemi.android.tutorial.widget.wechat.fragment.WeChatDiscoverFragment;
import com.lukemi.android.tutorial.widget.wechat.fragment.WeChatMSGFragment;
import com.lukemi.android.tutorial.widget.wechat.fragment.WeChatMineFragment;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioGroupMainActivity extends AppCompatActivity {


    @BindView(R.id.tab_weixin)
    RadioButton tabWeixin;
    @BindView(R.id.tab_contact)
    RadioButton tabContact;
    @BindView(R.id.tab_find)
    RadioButton tabFind;
    @BindView(R.id.tab_me)
    RadioButton tabMe;
    @BindView(R.id.rg_menu)
    RadioGroup rgMenu;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int[] drawableList = new int[]{
            R.drawable.selector_wechat_main_message,
            R.drawable.selector_wechat_main_contact,
            R.drawable.selector_wechat_main_discover,
            R.drawable.selector_wechat_main_mine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group_main);
        ButterKnife.bind(this);
        initData(savedInstanceState);
        initView();
        showFragment(0);
    }

    private void initView() {
        rgMenu.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    private void initData(Bundle savedInstanceState) {


       for (int i = 0; i < drawableList.length; i++) {
            RadioButton radioButton = (RadioButton) rgMenu.getChildAt(i);
            int drawable = drawableList[i];
            Drawable d = getResources().getDrawable(drawable);
            int px = DensityUtil.dp2px(16);
            d.setBounds(0, 0, px, px);
            ImageSpan imageSpan = new ImageSpan(d,
                                                       DynamicDrawableSpan.ALIGN_BASELINE);
            radioButton.setCompoundDrawables(null, d, null, null);

        }

        fragmentList.add(WeChatMSGFragment.newInstance("", ""));
        fragmentList.add(WeChatContactFragment.newInstance("", ""));
        fragmentList.add(WeChatDiscoverFragment.newInstance("", ""));
        fragmentList.add(WeChatMineFragment.newInstance("", ""));
    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            for (int i = 0; i < rgMenu.getChildCount(); i++) {
                if (rgMenu.getChildAt(i).getId() == checkedId) {
                    showFragment(i);
                }
            }
        }
    }

    private void showFragment(int i) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        for (int j = 0; j < fragmentList.size(); j++) {
            Fragment fragment = fragmentList.get(j);
            if (!fragment.isAdded()) {
                transaction.add(R.id.framelayout, fragment);
            }
            if (j == i) {
                transaction.show(fragment);
            } else {
                transaction.hide(fragment);
            }
        }

        transaction.commit();
    }
}
