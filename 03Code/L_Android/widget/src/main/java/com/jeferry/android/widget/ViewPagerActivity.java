package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
    }

    private void initView() {
        // 设置item点击无动画
        BottomNavigationView bnv1 = findViewById(R.id.bnv_1);
        bnv1.setItemTextAppearanceActive(R.style.bottom_navigation_view_active_size);
        bnv1.setItemTextAppearanceInactive(R.style.bottom_navigation_view_active_size);
    }
}
