package com.jeferry.android.widget.wechat.tablayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
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
    private TabLayout tl2;

    private String[] titleList = new String[]{"android", "ios", "python"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        initView();
        selectDefault(0);
    }

    private void initView() {
        tl = findViewById(R.id.tl);
        tl2 = findViewById(R.id.tl2);
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
        for (int i = 0; i < titleList.length; i++) {
            TabLayout.Tab tab = tl2.newTab();
            tab.setCustomView(R.layout.item_tab);
            ((TextView) tab.getCustomView().findViewById(R.id.tv_tab)).setText(titleList[i]);
            tl2.addTab(tab);
        }
        tl2.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String s = ((TextView) tab.getCustomView().findViewById(R.id.tv_tab)).getText().toString();
                ToastUtil.show_makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
                select(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void select(TabLayout.Tab selectTab) {
        for (int i = 0; i < tl2.getTabCount(); i++) {
            TabLayout.Tab tab = tl2.getTabAt(i);
            TextView viewById = tab.getCustomView().findViewById(R.id.tv_tab);
            boolean select = selectTab == tab;
            setTabAttrs(viewById, select, R.color.red, R.color.color_black);
        }
    }

    private void selectDefault(int index) {
        TabLayout.Tab tab = tl2.getTabAt(index);
        TextView viewById = tab.getCustomView().findViewById(R.id.tv_tab);
        setTabAttrs(viewById, true, R.color.red, R.color.color_black);
    }

    private void setTabAttrs(TextView viewById, boolean select, int p, int p2) {
        int size = select ? 16 : 14;
        int colorId = select ? p : p2;
        Typeface typeface = select ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT;
        viewById.setTextSize(size);
        viewById.setTextColor(ContextCompat.getColor(getApplication(), colorId));
        viewById.setTypeface(typeface);
    }
}
