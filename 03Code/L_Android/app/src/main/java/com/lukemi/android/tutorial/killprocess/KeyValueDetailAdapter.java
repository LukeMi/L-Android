package com.lukemi.android.tutorial.killprocess;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lukemi.android.tutorial.R;

import java.util.List;

public class KeyValueDetailAdapter extends BaseQuickAdapter<KeyValueDetailBean, BaseViewHolder> {

    public KeyValueDetailAdapter(int layoutResId, @Nullable List<KeyValueDetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyValueDetailBean item) {
        helper.setText(R.id.tv_key, item.getKey())
                .setText(R.id.tv_value, item.getValue());
    }
}
