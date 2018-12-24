package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.lukemi.android.tutorial.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lukemi
 * @date 2018/12/24 10:51
 * @des ViewStub 标签使用 布局优化
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class ViewStubActivity extends AppCompatActivity {
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //ViewStub id 只能用一次
        ViewStub viewById = findViewById(R.id.view_stub);
        if (viewById != null) {
            mView = viewById.inflate();
        }
        mView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_test)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                mView.setVisibility(mView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;
            default:
                break;
        }
    }
}