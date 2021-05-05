package com.lukemi.android.tutorial.rxJava;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class RXBaseActivity<V, T extends RxBasePresenter<V>> extends AppCompatActivity {
    private T present;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        getIntentData();
        initData(savedInstanceState);
        initView();
        bindPresent();
    }

    @Override
    protected void onDestroy() {
        present.onDetach();
        super.onDestroy();
    }

    private void initLayout() {
        if (bindLayout() != 0) {
            setContentView(bindLayout());
        } else {
            dateBind();
        }
        ButterKnife.bind(this);
    }

    /**
     * 数据绑定
     */
    protected void dateBind() {

    }

    /**
     * @return @LayoutRes
     */
    protected abstract int bindLayout();

    protected void getIntentData() {

    }

    protected void initData(Bundle savedInstanceState) {
    }

    protected void initView() {

    }


    private void bindPresent() {
        this.present = onBindPresent();
        present.onAttach((V) this);
    }

    public abstract T onBindPresent();


}
