package com.jeferry.android.widget.recycle.flexbox;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jeferry.android.widget.R;
import com.jeferry.android.widget.flexbox.FlexBoxEntity;

import java.util.List;

public class FlexboxAdapter extends BaseQuickAdapter<FlexBoxEntity, BaseViewHolder> {

    public FlexboxAdapter(int layoutResId, @Nullable List<FlexBoxEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FlexBoxEntity item) {
        helper.setText(R.id.tv_text, item.name)
                /*.addOnClickListener(R.id.tv_text)*/;
        helper.getView(R.id.tv_text).setSelected(item.selected);
    }
}
