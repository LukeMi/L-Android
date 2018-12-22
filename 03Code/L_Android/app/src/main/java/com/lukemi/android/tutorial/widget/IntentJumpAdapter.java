package com.lukemi.android.tutorial.widget;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lukemi.android.tutorial.R;

import java.util.List;

public class IntentJumpAdapter extends BaseQuickAdapter<IntentJumpBean, BaseViewHolder> {

    public IntentJumpAdapter(int layoutResId, @Nullable List<IntentJumpBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntentJumpBean item) {
        helper.setText(R.id.tv_tag, item.getText());
    }
}
