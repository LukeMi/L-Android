package com.lukemi.myandroid.viewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lukemi.myandroid.R;
import com.lukemi.myandroid.mvp_todaynews.TTDZAdapter;
import com.lukemi.myandroid.mvp_todaynews.TTDZBean;
import com.lukemi.myandroid.util.Logcat;
import com.lukemi.myandroid.view.pulltorefresh.RefreshHeaderView;
import com.lukemi.myandroid.view.pulltorefresh.PtrFrameLayout;
import com.lukemi.myandroid.view.pulltorefresh.PtrHandler;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class RVRefreshViewActivity extends AppCompatActivity implements RefreshHeaderView.RefreshDistanceListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.rv_test)
    RecyclerView rvTest;
    @BindView(R.id.rotate_header_list_view_frame)
    RefreshHeaderView mPtrFrame;
    private String API_TT_AS = "http://www.toutiao.com/api/article/feed/?category=essay_joke&as=A115C8457F69B85&cp=585F294B8845EE1";
    private int page;
    private Gson gson;
    private List<TTDZBean.DataBean> dataBeanList = new ArrayList<>();
    private TTDZAdapter ttdzAdapter;
    private boolean isRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvrefresh_view);
        ButterKnife.bind(this);
        initData();
        initView();
        initPtrFrame();
        refresh();
    }

    /**
     * 初始化下拉刷新
     */
    private void initPtrFrame() {
        mPtrFrame = (RefreshHeaderView) findViewById(R.id.rotate_header_list_view_frame);
        mPtrFrame.setOnRefreshDistanceListener(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refresh();
            }
        });

        // 是否进入页面就开始显示刷新动作
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);
    }

    private void initData() {
        gson = new Gson();
        ttdzAdapter = new TTDZAdapter(R.layout.item_ttdz, dataBeanList);
        ttdzAdapter.setOnLoadMoreListener(this, rvTest);
//        ttdzAdapter.setOnLoadMoreListener(this, xrecyclerView);
    }

    private void initView() {
        rvTest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTest.setAdapter(ttdzAdapter);
      /*  xrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrecyclerView.setArrowImageView(R.drawable.arrow);
        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onLoadMore() {

            }
        });
        xrecyclerView.setAdapter(ttdzAdapter);*/
    }

    private void refresh() {
        isRefresh = true;
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
                            Logcat.log("内涵段子返回--->" + new JSONObject(s));
                            TTDZBean ttdzBean = gson.fromJson(s, TTDZBean.class);
                            List<TTDZBean.DataBean> data = ttdzBean.getData();
                            if (data != null && data.size() > 0) {
                                ttdzAdapter.loadMoreComplete();
                                dataBeanList.addAll(data);
                                ttdzAdapter.notifyDataSetChanged();
                            } else {
                                ttdzAdapter.loadMoreEnd();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        if (isRefresh) {
                            isRefresh = false;
                            mPtrFrame.refreshComplete();
                        }
                        super.onAfter(s, e);
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    @Override
    public void onPositionChange(int currentPosY) {

    }
}
