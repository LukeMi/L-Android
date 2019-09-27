package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * @author mzchen
 * @date 2018/12/22 17:06
 * @des merge 标签使用 布局优化 <p/> <a href="https://www.cnblogs.com/dukc/p/5136310.html">参考</a>
 * @mail chenmingzhi@ccclubs.com / chenmingzhiji@163.com
 */
public class MergeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
    }
}
