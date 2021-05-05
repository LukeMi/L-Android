package com.jeferry.android.widget;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jeferry.android.widget.wechat.fragment.WeChatContactFragment;
import com.jeferry.android.widget.wechat.fragment.WeChatDiscoverFragment;
import com.jeferry.android.widget.wechat.fragment.WeChatMSGFragment;
import com.jeferry.android.widget.wechat.fragment.WeChatMineFragment;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class RadioGroupMainActivity extends AppCompatActivity {

    private RadioButton tabWX;
    private RadioButton tabContact;
    private RadioButton tabFind;
    private RadioButton tabMe;
    private RadioGroup rgMenu;

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
        initView();
        initData(savedInstanceState);
        showFragment(0);
    }

    private void initView() {
        tabWX = findViewById(R.id.tab_weixin);
        tabContact = findViewById(R.id.tab_contact);
        tabFind = findViewById(R.id.tab_find);
        tabMe = findViewById(R.id.tab_me);
        rgMenu = findViewById(R.id.rg_menu);
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
