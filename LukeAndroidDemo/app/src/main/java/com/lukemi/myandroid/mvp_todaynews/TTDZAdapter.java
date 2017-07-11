package com.lukemi.myandroid.mvp_todaynews;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lukemi.myandroid.R;

import java.util.List;

/**
 * Created by android on 2017/7/11.
 */

public class TTDZAdapter extends BaseQuickAdapter<TTDZBean.DataBean, BaseViewHolder> {

    public TTDZAdapter(@LayoutRes int layoutResId, @Nullable List<TTDZBean.DataBean> data) {
        super(layoutResId, data);
    }

    public TTDZAdapter(@Nullable List<TTDZBean.DataBean> data) {
        super(data);
    }

    public TTDZAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TTDZBean.DataBean item) {
        helper.setText(R.id.tv_content, item.getGroup().getText());
    }
}
