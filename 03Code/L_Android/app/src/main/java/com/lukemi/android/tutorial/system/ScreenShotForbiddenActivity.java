package com.lukemi.android.tutorial.system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
/**
 * @author lukemi
 * @date 2018/12/24 17:38
 * @des 禁止截屏
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
import com.lukemi.android.tutorial.R;

public class ScreenShotForbiddenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot_forbidden_acticity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
    }
}
