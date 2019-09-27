package com.jeferry.android.widget.wechat.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.jeferry.android.widget.R;
import com.lukemi.android.common.util.ToastUtil;


/**
 * @author mzchen
 * @date 2019/6/26 10:24
 * @des tablayout
 * @mail chenmingzhi@ccclubs.com / chenmingzhiji@163.com
 */
public class TabLayoutActivity extends AppCompatActivity {

    private TabLayout tl;

    private String[] titleList = new String[]{"android", "ios", "python"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        initView();

    }

    private void initView() {
        tl = findViewById(R.id.tl);
        for (String s : titleList) {
            TabLayout.Tab tab = tl.newTab();
            tab.setText(s);
            tl.addTab(tab);
        }
        tl.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ToastUtil.show_makeText(getApplicationContext(), tab.getText(), Toast.LENGTH_SHORT);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
