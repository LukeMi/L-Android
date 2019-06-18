package com.lukemi.android.tutorial.system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.widget.IntentJumpAdapter;
import com.lukemi.android.tutorial.widget.IntentJumpBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author mzchen
 * @date 2019/6/18 20:36
 * @des
 * @link https://www.cnblogs.com/huolongluo/p/7774870.html
 * @mail chenmingzhiji@163.com
 */
public class SystemShareActivity extends AppCompatActivity {

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;
    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = (BaseQuickAdapter adapter, View view, int position) -> {
        IntentJumpBean bean = (IntentJumpBean) adapter.getData().get(position);
        switch (bean.getFlag()) {
            case 1:
                textShare();
                break;
            default:
                break;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_share);
        initAdapter();
        initView();
    }

    private void initAdapter() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("文本 - 分享", null, 1));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }

    private void textShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "文本分享");
        startActivity(Intent.createChooser(intent, "这是内容，文本分享"));
    }
}
