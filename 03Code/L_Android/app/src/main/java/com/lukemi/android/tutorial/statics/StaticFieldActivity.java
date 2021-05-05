package com.lukemi.android.tutorial.statics;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

/**
 * @author lukemi
 * @date 2019/1/11 17:19
 * @des 测试静态变量被回收的问题
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class StaticFieldActivity extends AppCompatActivity {

    public static final String PARAMS = "params";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_field);
    }
}
