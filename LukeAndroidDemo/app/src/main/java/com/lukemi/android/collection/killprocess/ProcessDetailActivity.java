package com.lukemi.android.collection.killprocess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.lukemi.android.collection.bean.AppInfo;

public class ProcessDetailActivity extends AppCompatActivity {

    private LinearLayout lLayout;
    private AppInfo appInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_process_detail);
        getIntentData();
        initView();
    }

    /**
     *
     */
    private void getIntentData() {
        appInfo = (AppInfo) getIntent().getSerializableExtra("ProcessDetailActivity");
    }

    /**
     *
     */
    private void initView() {
        lLayout = ((LinearLayout) findViewById(com.lukemi.android.collection.R.id.content));
        TableLayout tlayout = new TableLayout(this);
        TableRow tRow = new TableRow(this);
        tlayout.addView(tRow);
        tlayout.addView(tlayout);
    }
}
