package com.lukemi.android.tutorial.status;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class StatusActivity extends AbsBaseActivity {

    @BindView(R.id.btn_status)
    Button btnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_status;
    }

    @Override
    protected void initView() {

        btnStatus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
                    changeStatusBar();
                return false;
            }
        });
    }

    @OnClick(R.id.btn_status)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_status:
//                changeStatusBar();
                break;
        }
    }

    boolean fullScreen = false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void changeStatusBar() {
        fullScreen = !fullScreen;
        Window window = this.getWindow();
        View decorView = window.getDecorView();

      /*  Scene contentScene = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            contentScene = getContentScene();
        }
        ViewGroup sceneRoot = contentScene.getSceneRoot();*/
        int flag = fullScreen ? View.SYSTEM_UI_FLAG_FULLSCREEN : View.SYSTEM_UI_FLAG_VISIBLE;
        btnStatus.setText(fullScreen ? "状态栏隐藏" : "状态栏显示");
        decorView.setSystemUiVisibility(flag);
    }

}
