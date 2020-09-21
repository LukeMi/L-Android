package com.lukemi.android.tutorial.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * onSaveInstanceState(Bundle outState)
 * onRestoreInstanceState(Bundle savedInstanceState)
 */
public class SaveInstanceStateActivity extends AppCompatActivity {

    private final String TAG = SaveInstanceStateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session2);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            boolean save = savedInstanceState.getBoolean("save", false);
            KLog.d(TAG, "save : " + save);
        }
    }

    @OnClick(R.id.task)
    public void onViewClicked() {
        Intent intent = new Intent(this, NewTaskActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 调用时机
     * 1、 当用户按下HOME键时。
     * 这是显而易见的，系统不知道你按下HOME后要运行多少其他的程序，自然也不知道activity A是否会被销毁，故系统会调用onSaveInstanceState，让用户有机会保存某些非永久性的数据。以下几种情况的分析都遵循该原则
     * 2、 长按HOME键，选择运行其他的程序时。
     * 3、 按下电源按键（关闭屏幕显示）时。
     * 4、 从activity A中启动一个新的activity时。
     * 5、 屏幕方向切换时，例如从竖屏切换到横屏时。
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putBoolean("save", true);
            KLog.d(TAG, "save : " + true + "");
        } else {
            KLog.d(TAG, "outState == null ? " + (outState == null));
        }
    }

    /**
     * 1. onSaveInstanceState方法和onRestoreInstanceState方法“不一定”是成对的被调用的
     * 2. onRestoreInstanceState被调用的前提是，activity A“确实”被系统销毁了
     * 3. onRestoreInstanceState的bundle参数也会传递到onCreate方法中()
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            KLog.d(TAG, savedInstanceState.getBoolean("save", false));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
}
