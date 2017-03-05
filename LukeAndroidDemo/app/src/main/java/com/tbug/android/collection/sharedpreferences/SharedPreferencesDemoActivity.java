package com.tbug.android.collection.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tbug.android.collection.R;


public class SharedPreferencesDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_demo);

        SharedPreferences sharedPreferences = this.getSharedPreferences("info",MODE_PRIVATE);
        sharedPreferences.edit().putString("TAG","TAG").commit();
    }
}
