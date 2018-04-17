package com.lukemi.myandroid.list_r;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.lukemi.myandroid.list_r.itemDecoration.DividerItemDecoration;

import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lukemi.myandroid.R;
import com.lukemi.myandroid.list_r.itemDecoration.GruopItemDecoration;
import com.lukemi.myandroid.util.Logcat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvDividerActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    // 测试 RecyclerView 的分割线
    private List<String> lists = new ArrayList<>();
    private DividerAdapter dividerAdapter;
    //测试 RecyclerView 的标题
    private List<StudentBean> studentBeanList = new ArrayList<>();
    private SAdapter sAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_divider);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        lists.add("a");
        lists.add("aa");
        lists.add("aa");
        lists.add("aaa");
        lists.add("aaaa");
        lists.add("aaaaa");
        lists.add("aaaaa");
        lists.add("aaaaaa");
        lists.add("aaaaaaa");
        lists.add("aaaaaaaa");
        lists.add("aaaaaaaaa");

        lists.add("ba");
        lists.add("baa");
        lists.add("baaa");
        lists.add("baaaa");
        lists.add("baaaaa");

        lists.add("cba");
        lists.add("cbaa");
        lists.add("cbaaa");
        lists.add("cbaaaa");
        lists.add("cbaaaaa");

        lists.add("dba");
        lists.add("dbaa");
        lists.add("dbaaa");
        lists.add("dbaaaa");
        lists.add("dbaaaaa");
        dividerAdapter = new DividerAdapter(this, R.layout.item_divider_rv, lists);


        studentBeanList.add(new StudentBean(6, "a61"));
        studentBeanList.add(new StudentBean(1, "a1"));
        studentBeanList.add(new StudentBean(2, "a21"));
        studentBeanList.add(new StudentBean(4, "a41"));
        studentBeanList.add(new StudentBean(3, "a331"));
        studentBeanList.add(new StudentBean(5, "a551"));
        studentBeanList.add(new StudentBean(2, "a221"));
        studentBeanList.add(new StudentBean(1, "a11"));
        studentBeanList.add(new StudentBean(3, "a31"));
        studentBeanList.add(new StudentBean(4, "a441"));
        studentBeanList.add(new StudentBean(5, "a51"));
        Collections.sort(studentBeanList);//排序
        for (int i = 0; i < studentBeanList.size(); i++) {
            Logcat.log("studentBeanList.get( " + i + " ).toString():" + studentBeanList.get(i).toString());
        }
        sAdapter = new SAdapter(this, R.layout.item_divider_rv, studentBeanList);


    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dd = new DividerItemDecoration(this);
        DividerItemDecoration dd = new DividerItemDecoration
                .Builder(this)
                .setDividerColorRes(R.color.black)
                .setDividerHeightRes(R.dimen.dp1)
                .create();

        android.support.v7.widget.DividerItemDecoration d = new android.support.v7.widget.DividerItemDecoration(this, android.support.v7.widget.DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dd);

        GruopItemDecoration gg = new GruopItemDecoration(this, mDecorationListener);
        rv.addItemDecoration(gg);


//        rv.setAdapter(dividerAdapter);
        rv.setAdapter(sAdapter);

    }


    /**
     * 适配器
     */
    class DividerAdapter extends RecyclerView.Adapter<DViewHolder> {

        private Context context;
        private LayoutInflater inflater;
        private List<String> lists;
        private int layoutRes;

        public DividerAdapter(@NonNull Context context, @LayoutRes int layoutRes, List<String> lists) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.lists = lists;
            this.layoutRes = layoutRes;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public DViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(layoutRes, null);
            return new DViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DViewHolder holder, int position) {
            String s = lists.get(position);
            holder.tvTitle.setText(s);
        }

        @Override
        public int getItemCount() {
            return lists == null ? 0 : lists.size();
        }
    }

    //

    class DViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public DViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }


    }


    /**
     * 适配器
     */
    class SAdapter extends RecyclerView.Adapter<DViewHolder> {

        private Context context;
        private LayoutInflater inflater;
        private List<StudentBean> lists;
        private int layoutRes;

        public SAdapter(@NonNull Context context, @LayoutRes int layoutRes, List<StudentBean> lists) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.lists = lists;
            this.layoutRes = layoutRes;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public DViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(layoutRes, null);
            return new DViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DViewHolder holder, int position) {
            StudentBean studentBean
                    = lists.get(position);
            holder.tvTitle.setText(studentBean.getName());
        }

        @Override
        public int getItemCount() {
            return lists == null ? 0 : lists.size();
        }
    }


    private GruopItemDecoration.DecorationListener mDecorationListener = new GruopItemDecoration.DecorationListener() {
        @Override
        public int getGroupId(int position) {
            return studentBeanList.get(position).getGrade();
        }

        @Override
        public String getGroupTitle(int position) {
            return String.valueOf(studentBeanList.get(position).getGrade());
        }
    };

}
