package com.lukemi.android.collection.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SharedPreferencesDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_shared_preferences_demo);

        SharedPreferences sharedPreferences = this.getSharedPreferences("info",MODE_PRIVATE);
        sharedPreferences.edit().putString("TAG","TAG").commit();
    }
}
