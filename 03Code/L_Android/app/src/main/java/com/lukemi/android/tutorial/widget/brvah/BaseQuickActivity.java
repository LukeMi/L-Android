package com.lukemi.android.tutorial.widget.brvah;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.bean.NewsCatesBean;
import com.lukemi.android.tutorial.bean.Village;
import com.lukemi.android.tutorial.util.CommonUtils;
import com.lukemi.android.tutorial.util.HttpUtils;
import com.lukemi.android.tutorial.util.Logcat;
import com.lukemi.android.tutorial.util.ToastUtil;
import com.lukemi.android.tutorial.view.DividerItemDecoration;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class BaseQuickActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_basequick)
    RecyclerView rvBasequick;
    @BindView(R.id.srl)
    SwipeRefreshLayout src;
    private List<Village> list = new ArrayList<>();
    private int count = 0;
    String url = "http://god.91360.com/api.php";
    private boolean isFirstRequest = true;
    private List<NewsCatesBean.DataBean.ListsBean.RowsBean> newLists = new ArrayList<>();
    private List<NewsCatesBean.DataBean.SlidesBean.RowsBeanX> slidesRows = new ArrayList<>();
    private QuickAdapter adapter;
    private int page = 1;
    private boolean isLoadMore;
    private View headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_quick);
        ButterKnife.bind(this);
//        initData();
        initView();
        httpRequest();


    }

    private void initView() {
        src.setColorSchemeColors(getResources().getColor(R.color.mediumaquamarine));
        src.setOnRefreshListener(this);
        adapter = new QuickAdapter(R.layout.item_newscat_type1, newLists);
        adapter.openLoadAnimation(null);
        rvBasequick.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        rvBasequick.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvBasequick.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this,rvBasequick);



    }

    private void httpRequest() {
        OkGo
                .get(url)
                .params("op", "app", false)
                .params("action", "lists", false)
                .params("jscbk", "direct", false)
                .params("catid", 20, false)
                .params("page", page, false)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            Logcat.log(s);
                            JSONObject obj = new JSONObject(s);
                            if (obj.optString("rsm").equals("0")) {
                                Exception e = new Exception(obj.optString("err"));
                                onError(call, response, e);
                            } else {
                                Gson gson = new Gson();
                                NewsCatesBean newsCatesBean = gson.fromJson(obj.toString(), NewsCatesBean.class);
                                List<NewsCatesBean.DataBean.ListsBean.RowsBean> lists = newsCatesBean.getData().getLists().getRows();
                                if (!isLoadMore) {
                                    newLists.clear();
                                }
                                newLists.addAll(lists);
                                if (lists!=null && lists.size()>0){
                                    adapter.loadMoreComplete();
                                }else {
                                    adapter.loadMoreEnd();
                                }
                                List<NewsCatesBean.DataBean.SlidesBean.RowsBeanX> rows = newsCatesBean.getData().getSlides().getRows();
                                if (rows!=null &&rows.size()>0){
                                    adapter.removeAllHeaderView();
                                    headView =  getLayoutInflater().inflate(R.layout.view_head_news,null/*(ViewGroup) rvBasequick.getParent(), false*/);
                                    adapter.addHeaderView(headView);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            onError(call, response, e);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        if (isFirstRequest) {
                            isFirstRequest = false;
                        }
                        if (isLoadMore) {
                            isLoadMore = false;
                        }
                        src.setEnabled(true);
                        adapter.setEnableLoadMore(true);
                        if (src.isRefreshing()) {
                            src.setRefreshing(false);
                        }
                    }
                });
    }


    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

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
            if (TextUtils.isEmpty(img)){
                 helper.getView(R.id.img_art).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.img_art).setVisibility(View.VISIBLE);
                CommonUtils.glideLoadPic(BaseQuickActivity.this, item.getThumb(), (ImageView) helper.getView(R.id.img_art));
            }
        }
    }


    /**
     * 下拉刷新
     */
    private void refresh() {
        page = 1;
       /* if (headView != null) {
            lvNews.removeHeaderView(headView);
        }*/
        httpRequest();
    }

    /**
     * 上拉加载
     */
    private void loadMore() {
        isLoadMore = true;
        page++;
        httpRequest();
    }
}
