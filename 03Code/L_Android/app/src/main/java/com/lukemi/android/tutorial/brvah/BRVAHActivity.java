package com.lukemi.android.tutorial.brvah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.android.tutorial.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BRVAHActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brvah);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_muliteItem)
    public void onViewClicked(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_muliteItem:
                intent = new Intent(this,MultieBRVAHActivity.class);
                break;
            default:
                break;
        }
        if (intent !=null){
            startActivity(intent);
        }
    }
}
