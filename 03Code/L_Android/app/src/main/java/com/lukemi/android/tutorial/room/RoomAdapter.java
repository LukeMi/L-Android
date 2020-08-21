package com.lukemi.android.tutorial.room;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.lukemi.android.tutorial.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomAdapter extends BaseQuickAdapter<BookEntity, BaseViewHolder> {

    public RoomAdapter() {
        super(R.layout.item_room);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookEntity item) {
        helper.setText(R.id.tv_name, item.name)
                .setText(R.id.tv_price, price(item.price))
                .setText(R.id.tv_time, time(item.date))
                .addOnClickListener(R.id.btn_delete);
        RecyclerView tag = helper.getView(R.id.rv_tag);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(mContext, FlexDirection.ROW, FlexWrap.WRAP);
        tag.setLayoutManager(flexboxLayoutManager);
        AuthorAdapter authorAdapter = new AuthorAdapter();
        authorAdapter.replaceData(item.list);
        tag.setAdapter(authorAdapter);
    }

    private String price(double price) {
        return String.format("价格 ： %.02f", price);
    }

    private String time(long time) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        String format = sd.format(new Date(time));
        return String.format("出版时间 ： %s", format);
    }

    private String time(Date time) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        String format = sd.format(time);
        return String.format("出版时间 ： %s", format);
    }
}
