package com.lukemi.android.tutorial.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lukemi.android.tutorial.widget.bean.SoftInputModeBean;
import com.lukemi.android.tutorial.widget.flowlayout.FlowLayout;
import com.lukemi.android.tutorial.widget.flowlayout.TagAdapter;

import java.util.List;

public class SoftInputModeAdapter extends TagAdapter<SoftInputModeBean> {

    private final LayoutInflater mLayoutInflater;
    private int mSoftMode;
    private int mLayoutRes;

    public SoftInputModeAdapter(List<SoftInputModeBean> datas, @LayoutRes int layoutRes, Context context, int softMode) {
        super(datas);
        mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutRes = layoutRes;
        this.mSoftMode = softMode;
    }

    @Override
    public View getView(FlowLayout parent, int position, SoftInputModeBean softInputModeBean) {
        TextView view = (TextView) mLayoutInflater.inflate(mLayoutRes, null);
        view.setText(softInputModeBean.getName());
        return view;
    }
}
