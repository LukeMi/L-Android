package com.lukemi.android.tutorial.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlowActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    FlowLayout fl;
    @BindView(R.id.scv)
    ScratchCardView scv;
    @BindView(R.id.img_cv)
    CircleView imgCv;
    private String[] item = new String[]{
            "虎", "狼", "鼠", "鹿", "貂", "猴", "貘", "树懒",
            "斑马", "狗", "狐", "熊", "无尾熊", "海豹", "鲸鱼",
            "穿山甲", "小熊猫", "麝牛", "猞猁", "鸭嘴兽", "食蚁兽"
            , "豹子", "长颈鹿"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        int padding = (int) getResources().getDimension(R.dimen.dp16);

        for (int i = 0; i < item.length; i++) {
            final TextView textView = new TextView(this);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 5;
            lp.topMargin = 5;
            lp.rightMargin = 5;
            lp.bottomMargin = 5;
            textView.setPadding(padding, padding, padding, padding);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.RED);
            textView.setLayoutParams(lp);
            textView.setText(item[i]);
            textView.setBackgroundColor(Color.parseColor("#dddddd"));
            fl.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(FlowActivity.this.getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        imgCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FlowActivity.this.getApplicationContext(), "fddddddddddd", Toast.LENGTH_SHORT).show();
            }
        });

        int childCount = fl.getChildCount();
        System.out.println("childCount: " + childCount);

    }

    @OnClick({R.id.img_cv, R.id.tv_restore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_cv:
//                System.out.println("fddddddddddd");
//                Toast.makeText(FlowActivity.this.getApplicationContext(), "fgdfg", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_restore:
                scv.requestLayout();
                scv.invalidate();
                break;
        }
    }
}
