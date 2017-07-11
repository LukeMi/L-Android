package com.lukemi.myandroid.viewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lukemi.myandroid.R;
import com.lukemi.myandroid.mvp_todaynews.TTDZAdapter;
import com.lukemi.myandroid.mvp_todaynews.TTDZBean;
import com.lukemi.myandroid.util.Logcat;
import com.lukemi.myandroid.view.RVRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class RVRefreshViewActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_test)
    RecyclerView rvTest;
    /*    @BindView(R.id.rv_refreshview)
        RVRefreshView rvRefreshview;*/
    private String API_TT_AS = "http://www.toutiao.com/api/article/feed/?category=essay_joke&as=A115C8457F69B85&cp=585F294B8845EE1";
    private int page;
    private Gson gson;
    private List<TTDZBean.DataBean> dataBeanList = new ArrayList<>();
    private TTDZAdapter ttdzAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvrefresh_view);
        ButterKnife.bind(this);
        initData();
        initView();
        refresh();
    }

    private void initData() {
        gson = new Gson();
        ttdzAdapter = new TTDZAdapter(R.layout.item_ttdz, dataBeanList);
        ttdzAdapter.setOnLoadMoreListener(this, rvTest);
    }

    private void initView() {
        rvTest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTest.setAdapter(ttdzAdapter);
    }

    private void refresh() {
        page = 1;
        dataBeanList.clear();
        httpRequest();
    }

    private void loadMore() {
        page++;
        httpRequest();
    }

    private void httpRequest() {
        OkGo.post(API_TT_AS)
                .tag(this)
                .params("jscbk", "direct", false)
                .params("page", page, false)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {

                            Logcat.log("内涵段子返回--->" + s);
                            TTDZBean ttdzBean = gson.fromJson(s, TTDZBean.class);
                            List<TTDZBean.DataBean> data = ttdzBean.getData();
                            if (data != null && data.size() > 0) {
                                dataBeanList.addAll(data);
                                ttdzAdapter.notifyDataSetChanged();
                                ttdzAdapter.loadMoreComplete();
                            } else {
                                ttdzAdapter.loadMoreEnd();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
//                        ExceptionUtil.handleException(RVRefreshViewActivity.this, e);
                        super.onError(call, response, e);
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }
}
