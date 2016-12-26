package com.luke.android.demo.testview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luke.android.demo.R;

public class CViewActivity extends AppCompatActivity implements View.OnClickListener{

    private Button eleSignBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cview);
        initView();
    }

    private void initView() {
        eleSignBTN = ((Button) findViewById(R.id.elecSign_CVA));
        eleSignBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id){
            case R.id.elecSign_CVA:
                intent.setClass(this,EleSignActivity.class);
                break;
            default:
                Toast.makeText(this, "这是什么鬼", Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(intent);
    }
}
