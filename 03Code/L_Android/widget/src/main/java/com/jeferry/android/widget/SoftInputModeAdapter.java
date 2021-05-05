package com.jeferry.android.widget;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jeferry.android.widget.bean.SoftInputModeBean;
import com.jeferry.android.widget.flowlayout.FlowLayout;
import com.jeferry.android.widget.flowlayout.TagAdapter;

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
