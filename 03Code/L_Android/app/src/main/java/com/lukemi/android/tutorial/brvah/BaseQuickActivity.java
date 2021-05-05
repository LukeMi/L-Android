package com.lukemi.android.tutorial.brvah;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lukemi.android.common.view.DividerItemDecoration;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.bean.NewsCatesBean;
import com.lukemi.android.tutorial.bean.Village;
import com.lukemi.android.tutorial.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseQuickActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener
          {

    private RecyclerView mRvBasequick;

    private SwipeRefreshLayout mSrl;

    private List<Village> list = new ArrayList<>();

    private List<NewsCatesBean.DataBean.ListsBean.RowsBean> newLists = new ArrayList<>();

    private QuickAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_quick);
        initView();
    }

    private void initView() {
        mSrl = findViewById(R.id.srl);
        mRvBasequick = findViewById(R.id.rv_basequick);

        mSrl.setColorSchemeColors(getResources().getColor(R.color.mediumaquamarine));
        mSrl.setOnRefreshListener(this);
        adapter = new QuickAdapter(R.layout.item_newscat_type1, newLists);
//        adapter.openLoadAnimation(null);
        mRvBasequick.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRvBasequick.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvBasequick.setAdapter(adapter);
//        adapter.setOnLoadMoreListener(this, mRvBasequick);
    }

    @Override
    public void onRefresh() {
//        adapter.setEnableLoadMore(false);
        refresh();
    }

/*    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }*/

    class QuickAdapter extends BaseQuickAdapter<NewsCatesBean.DataBean.ListsBean.RowsBean, BaseViewHolder> {
        public QuickAdapter(@LayoutRes int layoutResId, @Nullable List<NewsCatesBean.DataBean.ListsBean.RowsBean> data) {
            super(layoutResId, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, NewsCatesBean.DataBean.ListsBean.RowsBean item) {
            String date_author = item.getPublish() + "    " + item.getCopyfrom();
            helper.setText(R.id.text_title, item.getTitle())
                    .setText(R.id.text_date_author, date_author);
            String img = item.getThumb();
            if (TextUtils.isEmpty(img)) {
                helper.getView(R.id.img_art).setVisibility(View.GONE);
            } else {
                helper.getView(R.id.img_art).setVisibility(View.VISIBLE);
                CommonUtils.glideLoadPic(BaseQuickActivity.this, item.getThumb(), helper.getView(R.id.img_art));
            }
        }
    }


    /**
     * 下拉刷新
     */
    private void refresh() {


    }

    /**
     * 上拉加载
     */
    private void loadMore() {

    }
}
