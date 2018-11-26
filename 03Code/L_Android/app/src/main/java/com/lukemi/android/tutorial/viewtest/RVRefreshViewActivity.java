package com.lukemi.android.tutorial.viewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.mvp_todaynews.TTDZAdapter;
import com.lukemi.android.tutorial.mvp_todaynews.TTDZBean;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.view.DropDownMenu;
import com.lukemi.android.tutorial.view.SuperSwipeRefreshLayout;
import com.lukemi.android.tutorial.view.pulltorefresh.RefreshHeaderView;
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

    RecyclerView rvTest;
    SuperSwipeRefreshLayout superSwipeRefreshLayout;
    @BindView(R.id.dropDownView)
    DropDownMenu dropDownView;
    private String API_TT_AS = "http://www.toutiao.com/api/article/feed/";
    private String category = "essay_joke";
    private String as = "A115C8457F69B85";
    private String cp = "585F294B8845EE1";

    private int page;
    private Gson gson;
    private List<TTDZBean.DataBean> dataBeanList = new ArrayList<>();
    private TTDZAdapter ttdzAdapter;
    private boolean isRefresh;
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;
    private List<String> categories1 = new ArrayList<>();
    private List<String> categories2 = new ArrayList<>();
    private View contentView;
    private List<String> tabs = new ArrayList<>();
    private ArrayAdapter<String> cadater1;
    private ArrayAdapter<String> cadater2;
    private List<View> popViews = new ArrayList<>();
    private ListView lvcategories1;
    private ListView lvcategories2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvrefresh_view);
        ButterKnife.bind(this);
        contentView = LayoutInflater.from(this).inflate(R.layout.contentview_menu, null);
        rvTest = (RecyclerView) contentView.findViewById(R.id.mRecyclerView);
        superSwipeRefreshLayout = (SuperSwipeRefreshLayout) contentView.findViewById(R.id.rotate_header_list_view_frame);
        initData();
        initView();
        refresh();
    }

    /**
     * 初始化下拉刷新
     */
   /* private void initPtrFrame() {
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
    }*/
    private void initData() {
        tabs.add("分类一");
        tabs.add("分类er ");
        String[] stringArray1 = getResources().getStringArray(R.array.categoryList1);
        String[] stringArray2 = getResources().getStringArray(R.array.categoryList2);
        for (int i = 0; i < stringArray1.length; i++) {
            categories1.add(stringArray1[i]);
        }
        for (int i = 0; i < stringArray2.length; i++) {
            categories2.add(stringArray2[i]);
        }
        cadater1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories1);
        cadater2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories2);

        gson = new Gson();
        ttdzAdapter = new TTDZAdapter(R.layout.item_ttdz, dataBeanList);
//        ttdzAdapter.setMaxItemCount(2);
        ttdzAdapter.setOnLoadMoreListener(this, rvTest);


    }

    private void initView() {


        rvTest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTest.setAdapter(ttdzAdapter);
        superSwipeRefreshLayout.setHeaderView(createHeaderView());
        superSwipeRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                textView.setText("正在刷新");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                refresh();
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                textView.setText(enable ? "松开刷新" : "下拉刷新");
                imageView.setVisibility(View.VISIBLE);
                imageView.setRotation(enable ? 180 : 0);
            }
        });


        lvcategories1 = new ListView(this);
        lvcategories1.setBackgroundColor(getResources().getColor(R.color.white));
        lvcategories1.setAdapter(cadater1);
        lvcategories1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String curcategory = categories1.get(position);
                doSort(curcategory);
                dropDownView.closeMenu();
            }
        });
        lvcategories2 = new ListView(this);
        lvcategories2.setBackgroundColor(getResources().getColor(R.color.white));
        lvcategories2.setAdapter(cadater2);

        lvcategories2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String curcategory = categories2.get(position);
                doSort(curcategory);
                dropDownView.closeMenu();
            }
        });

        popViews.add(lvcategories1);
        popViews.add(lvcategories2);
        dropDownView.setDropDownMenu(tabs, popViews, contentView);

    }


    private void doSort(String curcategory) {
        Toast.makeText(RVRefreshViewActivity.this, curcategory, Toast.LENGTH_SHORT).show();
        category = curcategory;
        refresh();
    }

    private View createHeaderView() {
        View headerView = LayoutInflater.from(superSwipeRefreshLayout.getContext())
                                  .inflate(R.layout.layout_head, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.arrow);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }

    private void refresh() {
        isRefresh = true;
        page = 1;
        httpRequest();
    }

    private void loadMore() {
        page++;
        httpRequest();
    }

    private void httpRequest() {
        OkGo.get(API_TT_AS)
                .tag(this)
                .params("category", category, false)
                .params("as", as, false)
                .params("cp", cp, false)
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
                            if (isRefresh) {
                                dataBeanList.clear();
                            }
                            if (data != null && data.size() > 0) {
                                if (ttdzAdapter.isLoading()) {
                                    ttdzAdapter.loadMoreComplete();
                                }

                                dataBeanList.addAll(data);
                                ttdzAdapter.notifyDataSetChanged();
                            } else {
                                if (ttdzAdapter.isLoading()) {
                                    ttdzAdapter.loadMoreEnd();
                                }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        page--;
                        super.onError(call, response, e);
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        if (isRefresh) {
                            isRefresh = false;
                            superSwipeRefreshLayout.setRefreshing(false);
                            progressBar.setVisibility(View.GONE);
                        }
                        if (ttdzAdapter.isLoading()) {
                            ttdzAdapter.loadMoreFail();
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
