package com.lukemi.android.tutorial.system;

import android.os.Bundle;
import android.os.Process;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lukemi
 * @date 2019/2/15 17:33
 * @des 关闭应用界面
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class CloseAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_app);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_close)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                /**
                 * 调用此方法确保 栈 里没有其他 activity
                 */
                System.exit(0);
                Process.killProcess(Process.myPid());
                break;
            default:
                break;
        }
    }
}
