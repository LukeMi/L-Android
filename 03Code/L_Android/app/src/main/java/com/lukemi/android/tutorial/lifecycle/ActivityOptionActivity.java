package com.lukemi.android.tutorial.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

import com.lukemi.android.tutorial.R;

import org.jetbrains.annotations.NotNull;

public class ActivityOptionActivity extends AppCompatActivity {

    public static final void start(Context context, @NotNull ActivityOptionsCompat optionsCompat) {
        Intent intent = new Intent(context, ActivityOptionActivity.class);
        ContextCompat.startActivity(context, intent, optionsCompat.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
    }
}