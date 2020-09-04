package com.lukemi.android.common;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

public class IntentJumpAdapter extends BaseQuickAdapter<IntentJumpBean, BaseViewHolder> {

    public IntentJumpAdapter() {
        super(R.layout.item_intent_jump);
    }

    public IntentJumpAdapter(int layoutResId, @Nullable List<IntentJumpBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntentJumpBean item) {
        helper.setText(R.id.tv_tag, item.getText());
    }
}
