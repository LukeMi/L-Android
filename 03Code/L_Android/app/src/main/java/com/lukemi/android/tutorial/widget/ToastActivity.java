package com.lukemi.android.tutorial.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author mzchen
 * @date 2019/7/17 19:59
 * @des Toast
 * @mail
 */
public class ToastActivity extends AppCompatActivity {

    private Button mBtnToast;
    private String[] strings = new String[]{"第一条吐司", "第二条吐司", "第三条吐司",
            "第四条吐司", "第五条吐司"};
    private int count;
    private Disposable mSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        initView();
    }

    @Override
    protected void onDestroy() {
        if (mSubscribe != null && !mSubscribe.isDisposed()) {
            mSubscribe.dispose();
        }
        super.onDestroy();
    }

    private void initView() {
        mBtnToast = findViewById(R.id.btn_toast);
        findViewById(R.id.btn_c_toast).setOnClickListener(this::onClick);
        mBtnToast.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toast:
                ToastUtil.show_makeText(getApplicationContext(), "吐司框", Toast.LENGTH_SHORT);
                break;
            case R.id.btn_c_toast:
                cToast();
                break;
            default:
                break;
        }

    }

    private void cToast() {
        ToastUtil.toast(strings[count]);
        mSubscribe = Observable.interval(1000, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    System.out.printf("aLong : " + aLong);
                    int length = strings.length;
                    ToastUtil.toast(strings[count]);
                    count++;
                    if (count == length) {
                        count = 0;
                    }
                }, throwable -> {

                });

    }
}
