package com.jeferry.android.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lukemi.android.common.view.CircleView;
import com.lukemi.android.common.view.FlowLayout;
import com.lukemi.android.common.view.ScratchCardView;


public class FlowActivity extends AppCompatActivity {

    FlowLayout fl;
    ScratchCardView scv;
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
        initView();
    }

    private void initView() {

        fl = findViewById(R.id.fl);
        scv = findViewById(R.id.scv);
        imgCv = findViewById(R.id.img_cv);
        findViewById(R.id.img_cv).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv_restore).setOnClickListener(this::onViewClicked);

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
                    Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        imgCv.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "fddddddddddd", Toast.LENGTH_SHORT).show());
        int childCount = fl.getChildCount();
        System.out.println("childCount: " + childCount);

    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.img_cv) {
//                System.out.println("fddddddddddd");
//                Toast.makeText(FlowActivity.this.getApplicationContext(), "fgdfg", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tv_restore) {
            scv.requestLayout();
            scv.invalidate();
        }
    }
}
