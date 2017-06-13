package com.lukemi.myandroid.widget.brvah;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lukemi.myandroid.R;
import com.lukemi.myandroid.bean.Village;
import com.lukemi.myandroid.util.ToastUtil;
import com.lukemi.myandroid.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseQuickActivity extends AppCompatActivity {

    @BindView(R.id.rv_basequick)
    RecyclerView rvBasequick;
    private List<Village> list = new ArrayList<>();
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_quick);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        rvBasequick.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        for (int i = 0; i < 50; i++) {
            Village v = new Village();
            count++;
            v.setId(count);
            v.setName("中国: " + count);
            list.add(v);
        }
        final QuickAdapter quickAdapter = new QuickAdapter(R.layout.item_quick_brvah, list);
        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String msg = "点击：" + position;
                ToastUtil.show_makeText(BaseQuickActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        quickAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                String msg = "长按：" + position;
                ToastUtil.show_makeText(BaseQuickActivity.this, msg, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        quickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                for (int i = 0; i < 50; i++) {
                    Village v = new Village();
                    v.setId(count++);
                    v.setName("中国: " + count);
                    list.add(v);
                }
//                quickAdapter.notifyDataSetChanged();
            }
        },rvBasequick);
        rvBasequick.setAdapter(quickAdapter);
        rvBasequick.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    class QuickAdapter extends BaseQuickAdapter<Village, BaseViewHolder> {

        public QuickAdapter(@LayoutRes int layoutResId, @Nullable List<Village> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Village item) {
            helper.setText(R.id.tv_id, item.getId() + "")
                    .setText(R.id.tv_name, item.getName());
        }
    }

}
