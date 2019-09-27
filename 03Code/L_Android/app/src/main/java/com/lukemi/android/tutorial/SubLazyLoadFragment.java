package com.lukemi.android.tutorial;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lukemi.android.common.base.BaseLazyFragment;
import com.lukemi.android.common.util.Logcat;
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
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubLazyLoadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubLazyLoadFragment extends BaseLazyFragment
        implements BaseQuickAdapter.RequestLoadMoreListener,
        OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.mRecyclerView)
    RecyclerView rvTest;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private boolean isFirstView = true;


    private String mParam1;
    private String mParam2;

    private String url = "http://www.toutiao.com/api/article/feed/";
    private String category = "news_society";
    private String as = "A115C8457F69B85";
    private String cp = "585F294B8845EE1";
    private int page = 1;
    private NewsAdapter newsAdapter;
    private Map<String, Class> targetMap = new HashMap<String, Class>();
    private List<JNewsBaseBean> newsList = new ArrayList<>();
    private boolean isRefresh;
    private Gson gson;
    private AppCompatActivity context;
    private View view;
    private boolean isCreatView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sub_lazy_load, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDate();
        initView();
        isCreatView = true;
        if (getUserVisibleHint()) {
            onLazyLoad();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void onLazyLoad() {
        Logcat.log("BaseLazyFragment " + "onLazyLoad");
        if (isCreatView && isFirstView) {
            isFirstView = false;
            try {
                refreshLayout.autoRefresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        rvTest.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvTest.setAdapter(newsAdapter);
        initRecyclerViewListener();
        refreshLayout.setRefreshHeader(new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate));
        refreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerViewListener() {
        ViewTreeObserver viewTreeObserver = rvTest.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                try {
                    if (rvTest.computeVerticalScrollRange() < getResources().getDisplayMetrics().heightPixels) {
                        newsAdapter.setEnableLoadMore(false);
                    } else {
                        newsAdapter.setEnableLoadMore(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SubLazyLoadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubLazyLoadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubLazyLoadFragment newInstance(String param1, String param2) {
        SubLazyLoadFragment fragment = new SubLazyLoadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

    private void initDate() {
        gson = new Gson();
        context = getActivity();
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
        newsAdapter.setOnLoadMoreListener(this, rvTest);
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
                        if (page != 1) {
                            page--;
                        }
                        super.onError(call, response, e);
                        if (newsAdapter.isLoading()) {
                            newsAdapter.loadMoreFail();
                        }
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        try {
                            if (isRefresh) {
                                isRefresh = false;
                            }
                            if (refreshLayout.isRefreshing()) {
                                refreshLayout.finishRefresh();
                            }
                            refreshLayout.setEnableRefresh(true);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
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
        loadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
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
