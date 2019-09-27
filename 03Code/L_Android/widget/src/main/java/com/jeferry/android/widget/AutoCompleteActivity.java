package com.jeferry.android.widget;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


public class AutoCompleteActivity extends AppCompatActivity {

    private static final String[] CONTENT = {"android", "ios", "java", "html", "angrialjs"};

    AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_coplete);
    }


    protected void initView() {
        actv = findViewById(R.id.actv_);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, CONTENT);
        actv.setAdapter(stringArrayAdapter);
        actv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast toast = Toast.makeText(AutoCompleteActivity.this, "position : " + CONTENT[i], Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        });
    }

}
