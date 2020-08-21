package com.lukemi.android.tutorial.room;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lukemi.android.tutorial.R;

public class AuthorAdapter extends BaseQuickAdapter<AuthorEntity, BaseViewHolder> {

    public AuthorAdapter() {
        super(R.layout.item_author);
    }

    @Override
    protected void convert(BaseViewHolder helper, AuthorEntity item) {
        helper.setText(R.id.tv_tag, item.name);
    }
}
