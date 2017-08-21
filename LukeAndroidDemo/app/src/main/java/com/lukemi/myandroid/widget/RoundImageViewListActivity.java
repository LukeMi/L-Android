package com.lukemi.myandroid.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lukemi.myandroid.BaseActivity;
import com.lukemi.myandroid.R;
import com.lukemi.myandroid.bean.DZBean;
import com.lukemi.myandroid.util.CommonUtils;
import com.lukemi.myandroid.util.HttpUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class RoundImageViewListActivity extends BaseActivity implements OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String url = "http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-102&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82&am_loc_time=1489226058493&count=30&min_time=1489205901&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120";
    private int page = 1;
    private MyAdapter adapter;
    private List<DZBean.DataBeanX.DataBean> list = new ArrayList<>();
    private boolean isRefresh;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_image_view_list);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        gson = new Gson();
        adapter = new MyAdapter(R.layout.item_process, list, this);
        adapter.setOnItemClickListener(this);
        adapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);
        refreshLayout.autoRefresh();
    }

    private void refresh() {
        isRefresh = true;
        adapter.setEnableLoadMore(false);
        page = 1;
        http();


    }

    private void loadMore() {
        page++;
        refreshLayout.setEnableRefresh(false);
        http();
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }


    private class MyAdapter extends BaseQuickAdapter<DZBean.DataBeanX.DataBean, BaseViewHolder> {
        private Context context;

        public MyAdapter(@LayoutRes int layoutResId, @Nullable List<DZBean.DataBeanX.DataBean> data, @Nullable Context context) {
            super(layoutResId, data);
            this.context = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, DZBean.DataBeanX.DataBean item) {
            DZBean.DataBeanX.DataBean.GroupBean group = item.getGroup();
            DZBean.DataBeanX.DataBean.GroupBean.UserBean user = group.getUser();
            String name = user.getName();
            String user_id = user.getUser_id();
            String avatar_url = user.getAvatar_url();
            helper.setText(R.id.title,name)
                    .setText(R.id.pName,user_id);
            CommonUtils.glideLoadPic(context,avatar_url, (ImageView) helper.getView(R.id.icon));
        }
    }

    private void http() {
        OkGo.get(url)
                .tag(this)
                .params("page", page, false)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        setList(s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        if (page != 1) {
                            page--;
                        }
                        adapter.loadMoreFail();
                        super.onError(call, response, e);
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        isRefresh = false;
                        if (refreshLayout != null) {
                            refreshLayout.setEnableRefresh(true);
                            if (refreshLayout.isRefreshing()) {
                                refreshLayout.finishRefresh();
                            }
                        }

                    }
                });
    }


    private void setList(String s) {
        try {
            DZBean dzBean = gson.fromJson(s, DZBean.class);
            List<DZBean.DataBeanX.DataBean> data = dzBean.getData().getData();
            if (isRefresh) {
                list.clear();
            }
            if (data != null && data.size() > 0) {
                list.addAll(data);
                adapter.loadMoreComplete();
            } else {
                adapter.loadMoreEnd();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }
}
