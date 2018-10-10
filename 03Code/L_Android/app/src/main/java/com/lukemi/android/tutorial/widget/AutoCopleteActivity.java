package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;

import butterknife.BindView;

public class AutoCopleteActivity extends BaseActivity {

    private static final String[] CONTENT = {"android", "ios", "java", "html", "angrialjs"};
    @BindView(R.id.actv_)
    AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_auto_coplete;
    }

    @Override
    protected void initView() {
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, CONTENT);
        actv.setAdapter(stringArrayAdapter);
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(AutoCopleteActivity.this, "position : " + CONTENT[i], Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

}
