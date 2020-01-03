package com.jeferry.android.widget.recycle.itemdecoration;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.jeferry.android.widget.R;

import java.util.Arrays;

public class RecyclerViewActivity extends AppCompatActivity {

    private String[] strings = new String[]{"Python", "JAVA", "NodeJS", "Java Script"};

    private RecyclerView rvShow;
    private RecyclerView rv1;
    private RecyclerView rv2;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reccycler_view);
        initView();
    }

    private void initView() {
        rvShow = findViewById(R.id.rv_show);
        rv1 = findViewById(R.id.rv1);
        rv2 = findViewById(R.id.rv2);
        swipeRefresh = findViewById(R.id.swipe_refresh);

        RecyclerAdapter recycleAdapter = new RecyclerAdapter(this, Arrays.asList(strings));

        rvShow.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置Adapter
        rvShow.setAdapter(recycleAdapter);
        // 设置分隔线
        rvShow.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        // 设置增加或删除条目的动画
        rvShow.setItemAnimator(new DefaultItemAnimator());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayout.HORIZONTAL, false);
        rv1.setLayoutManager(gridLayoutManager);
        rv1.setAdapter(recycleAdapter);

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP);
        rv2.setLayoutManager(flexboxLayoutManager);
        rv2.setAdapter(recycleAdapter);

    }

}
