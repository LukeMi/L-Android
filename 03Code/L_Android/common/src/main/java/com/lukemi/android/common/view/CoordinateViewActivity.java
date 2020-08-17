package com.lukemi.android.common.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lukemi.android.common.R;

import java.util.ArrayList;
import java.util.List;


public class CoordinateViewActivity extends AppCompatActivity {

    CoordinateView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate_view);
        cv = findViewById(R.id.cv);
        initData(savedInstanceState);
    }

    private void initData(Bundle savedInstanceState) {
        List<DataBean> dataBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataBean dataBean = null;
            if (i == 4) {
                dataBean = new DataBean(i * 100, 20, "data : " + i);
            } else if (i == 6) {
                dataBean = new DataBean(i * 100, i * 100 + 400, "data : " + i);
            } else {
                dataBean = new DataBean(i * 100, i * 100, "data : " + i);
            }
            dataBeanList.add(dataBean);
        }
        cv.setDataBeanList(dataBeanList);
    }

}
