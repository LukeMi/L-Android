package com.lukemi.android.tutorial.news_today;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.news_today.model.JNewsBabyBean;
import com.lukemi.android.tutorial.news_today.model.JNewsBaseBean;
import com.lukemi.android.tutorial.news_today.model.JNewsCarBean;
import com.lukemi.android.tutorial.news_today.model.JNewsDiscoveryBean;
import com.lukemi.android.tutorial.news_today.model.JNewsENTBean;
import com.lukemi.android.tutorial.news_today.model.JNewsEssayBean;
import com.lukemi.android.tutorial.news_today.model.JNewsFashionBean;
import com.lukemi.android.tutorial.news_today.model.JNewsFinanceBean;
import com.lukemi.android.tutorial.news_today.model.JNewsFoodBean;
import com.lukemi.android.tutorial.news_today.model.JNewsGameBean;
import com.lukemi.android.tutorial.news_today.model.JNewsHistoryBean;
import com.lukemi.android.tutorial.news_today.model.JNewsHotBean;
import com.lukemi.android.tutorial.news_today.model.JNewsMilBean;
import com.lukemi.android.tutorial.news_today.model.JNewsRecBean;
import com.lukemi.android.tutorial.news_today.model.JNewsRegimenBean;
import com.lukemi.android.tutorial.news_today.model.JNewsSocialBean;
import com.lukemi.android.tutorial.news_today.model.JNewsSportBean;
import com.lukemi.android.tutorial.news_today.model.JNewsStoryBean;
import com.lukemi.android.tutorial.news_today.model.JNewsTechBean;
import com.lukemi.android.tutorial.news_today.model.JNewsTravelBean;
import com.lukemi.android.tutorial.news_today.model.JNewsWorldBean;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.view.DropDownMenu;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class NewListActivity extends AppCompatActivity implements OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.dropDownView)
    DropDownMenu dropDownMenu;
    SmartRefreshLayout refreshLayout;
    private String url = "http://www.toutiao.com/api/article/feed/";
    private String category = "news_society";
    private String as = "A115C8457F69B85";
    private String cp = "585F294B8845EE1";
    private int page = 1;
    private boolean isRefresh;
    private View contentView;
    private RecyclerView mRecyclerView;
    private Gson gson;
    private Map<String, Class> targetMap = new HashMap<String, Class>();
    private List<JNewsBaseBean> newsList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private List<String> tabs = new ArrayList<>();
    private List<View> popViews = new ArrayList<>();
    private ListView lvcategories1;
    private ListView lvcategories2;
    private ArrayAdapter<String> cadater1;
    private ArrayAdapter<String> cadater2;
    private List<String> categories1 = new ArrayList<>();
    private List<String> categories2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        gson = new Gson();
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
        lvcategories1 = new ListView(this);
        lvcategories1.setBackgroundColor(getResources().getColor(R.color.white));
        lvcategories1.setAdapter(cadater1);
        lvcategories1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = categories1.get(position);
                dropDownMenu.setTabText(cadater1.getItem(position));
                dropDownMenu.closeMenu();
                mRecyclerView.smoothScrollToPosition(0);
                refreshLayout.autoRefresh();
            }
        });
        lvcategories2 = new ListView(this);
        lvcategories2.setBackgroundColor(getResources().getColor(R.color.white));
        lvcategories2.setAdapter(cadater2);

        lvcategories2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = categories2.get(position);
                dropDownMenu.setTabText(cadater2.getItem(position));
                dropDownMenu.closeMenu();
                mRecyclerView.smoothScrollToPosition(0);
                refreshLayout.autoRefresh();
            }
        });


        targetMap.put("__all__", JNewsRecBean.class);
        targetMap.put("news_hot", JNewsHotBean.class);
        targetMap.put("news_society", JNewsSocialBean.class);
        targetMap.put("news_entertainment", JNewsENTBean.class);
        targetMap.put("news_tech", JNewsTechBean.class);
        targetMap.put("news_military", JNewsMilBean.class);
        targetMap.put("news_sports", JNewsSportBean.class);
        targetMap.put("news_car", JNewsCarBean.class);
        targetMap.put("news_finance", JNewsFinanceBean.class);
        targetMap.put("news_world", JNewsWorldBean.class);
        targetMap.put("news_fashion", JNewsFashionBean.class);
        targetMap.put("news_travel", JNewsTravelBean.class);
        targetMap.put("news_discovery", JNewsDiscoveryBean.class);
        targetMap.put("news_baby", JNewsBabyBean.class);
        targetMap.put("news_regimen", JNewsRegimenBean.class);
        targetMap.put("news_story", JNewsStoryBean.class);
        targetMap.put("news_essay", JNewsEssayBean.class);
        targetMap.put("news_game", JNewsGameBean.class);
        targetMap.put("news_history", JNewsHistoryBean.class);
        targetMap.put("news_food", JNewsFoodBean.class);
        newsAdapter = new NewsAdapter(android.R.layout.simple_list_item_1, newsList);
        newsAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    private void initView() {
        contentView = LayoutInflater.from(this).inflate(R.layout.contentview_menu1, null);
        popViews.add(lvcategories1);
        popViews.add(lvcategories2);
        dropDownMenu.setDropDownMenu(tabs, popViews, contentView);

        mRecyclerView = (RecyclerView) contentView.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(newsAdapter);
        initRecyclerViewListener();
        refreshLayout = (SmartRefreshLayout) contentView.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this).setSpinnerStyle(SpinnerStyle.Translate));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.autoRefresh();
    }

    private void initRecyclerViewListener() {
        ViewTreeObserver viewTreeObserver = mRecyclerView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mRecyclerView.computeVerticalScrollRange() < getResources().getDisplayMetrics().heightPixels) {
                    Logcat.log("initRecyclerViewListener: sliceAdapter.setEnableLoadMore(false)");
                    newsAdapter.setEnableLoadMore(false);
                } else {
                    Logcat.log("initRecyclerViewListener: sliceAdapter.setEnableLoadMore(true)");
                    newsAdapter.setEnableLoadMore(true);
                }
            }
        });
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
        OkGo.get(url)
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
                        Logcat.log("今日头条返回--->" + s);
                        try {
                            JSONObject object = new JSONObject(s);
                            JSONArray array = object.getJSONArray("data");
                            if (array == null || array.length() < 1) {
                                newsAdapter.loadMoreEnd();
                            } else {
                                newsAdapter.loadMoreComplete();
                            }
                            Class<?> transformClass = getTagetClass(category);
                            if (transformClass != null && JNewsBaseBean.class.isAssignableFrom(transformClass)) {
                                List<JNewsBaseBean> terms = new ArrayList<JNewsBaseBean>();
                                for (int i = 0; i < array.length(); i++) {
                                    JNewsBaseBean o = (JNewsBaseBean) gson.fromJson(array.get(i).toString(), transformClass);
                                    terms.add(o);
                                }
                                if (isRefresh) {
                                    newsList.clear();
                                }
                                newsList.addAll(terms);
                                newsAdapter.notifyDataSetChanged();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        page--;
                        super.onError(call, response, e);
                        if (newsAdapter.isLoading()) {
                            newsAdapter.loadMoreFail();
                        }
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        if (isRefresh) {
                            isRefresh = false;
                        }
                        if (refreshLayout.isRefreshing()) {
                            refreshLayout.finishRefresh();
                        }
                        refreshLayout.setEnableRefresh(true);
                        super.onAfter(s, e);
                    }
                });
    }

    private Class<?> getTagetClass(@NonNull String key) {

        for (Map.Entry<String, Class> entry : targetMap.entrySet()) {
            if (TextUtils.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnableRefresh(false);
        Logcat.log("onLoadMoreRequested: ");
        loadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        newsAdapter.setEnableLoadMore(false);
        Logcat.log("onRefresh");
        refresh();
    }

    static class NewsAdapter extends BaseQuickAdapter<JNewsBaseBean, BaseViewHolder> {

        public NewsAdapter(@LayoutRes int layoutResId, @Nullable List<JNewsBaseBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, JNewsBaseBean item) {
            helper.setText(android.R.id.text1, item.toString());
        }
    }
}
