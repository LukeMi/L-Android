package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

/**
 * TableLayout布局学习
 * <p>
 * created bt: tubg
 * created at: 2017/3/28 18:16
 * <p>
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class TableLayoutActivity extends AppCompatActivity {

    private TableLayout osTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        initView();
    }

    /**
     * 初始化控件
     *
     * @author tbug
     * created at 2017/3/28 18:17
     */
    private void initView() {
        osTabLayout = findViewById(R.id.mTablelayout);
    }
}
