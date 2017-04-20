package com.lukemi.android.collection.plug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlugActivity extends AppCompatActivity {
    @BindView(com.lukemi.android.collection.R.id.buternifeBTN1)
    Button buternifeBTN1;
    @BindView(com.lukemi.android.collection.R.id.buternifeBTN2)
    Button buternifeBTN2;
    @BindView(com.lukemi.android.collection.R.id.buternifeBTN3)
    Button buternifeBTN3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_plug);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick({com.lukemi.android.collection.R.id.buternifeBTN1, com.lukemi.android.collection.R.id.buternifeBTN2, com.lukemi.android.collection.R.id.buternifeBTN3})
    public void onClick(View view) {
        switch (view.getId()) {
            case com.lukemi.android.collection.R.id.buternifeBTN1:
                Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
                break;
            case com.lukemi.android.collection.R.id.buternifeBTN2:
                Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
                break;
            case com.lukemi.android.collection.R.id.buternifeBTN3:
                Toast.makeText(this, "d", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
