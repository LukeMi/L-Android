package com.lukemi.android.tutorial;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lukemi.android.common.util.Logcat;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 欢迎页面设计<br/>
 * 功能点：延时跳转/点击跳转/TextView显示不同颜色文字
 * 坑点：解决启动时候白屏黑屏的问题；
 * <p>
 * <p>
 * created bt: tubg
 * created at: 2017/3/31 14:04
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class LauncherActivity extends AppCompatActivity implements View.OnClickListener {

    private final int MSG_DELAY = 0x0001;
    private ImageView welcIV;
    private TextView countDownTV;
    private ProgressBar showProcessbar;
    private String picURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491035800721&di=e447ef2886c8c4fe81225ac2d6c357bd&imgtype=0&src=http%3A%2F%2Fimg.warting.com%2Fallimg%2F2016%2F0711%2Fvim241exxwa-763.jpg";
    private int countTime = 3;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_DELAY:

                    String textStr = countTime + " 跳转";
                    SpannableStringBuilder ssb = new SpannableStringBuilder(textStr);
                    ssb.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.setSpan(new ForegroundColorSpan(Color.RED), 1, textStr.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    countDownTV.setText(ssb);

                    if (countTime == 0) {
                        mHandler.removeMessages(MSG_DELAY);
                        go2Main();
                    } else {
                        countTime -= 1;
                        mHandler.sendEmptyMessageDelayed(MSG_DELAY, 1000);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initView();
        mHandler.sendEmptyMessage(MSG_DELAY);
    }

    private void updateJumpText(){
        new
    }

    @Override
    protected void onDestroy() {
        if (mHandler.hasMessages(MSG_DELAY)) {
            mHandler.removeMessages(MSG_DELAY);
        }
        super.onDestroy();
    }

    /**
     * 初始化控件
     * <p>
     * created by: tbug
     * created at: 2017/3/31 13:42
     */
    private void initView() {
        welcIV = findViewById(R.id.welcIV);
        countDownTV = findViewById(R.id.countDownTV);
        showProcessbar = findViewById(R.id.showProcessbar);

        showProcessbar.setMax(100);
        showProcessbar.setProgress(0);
        countDownTV.setOnClickListener(this);
    }

    /**
     * 跳转到主界面
     * <p>
     * created by: tbug
     * created at: 2017/3/31 14:44
     */
    private void go2Main() {
        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.countDownTV:
                go2Main();
                break;
            default:
                break;
        }
    }
}
