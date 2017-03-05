package com.tbug.android.collection.plug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tbug.android.collection.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlugActivity extends AppCompatActivity {

    @Bind(R.id.buternifeBTN1)
    Button b;
    @Bind(R.id.buternifeBTN2)
    Button c;
    @Bind(R.id.buternifeBTN3)
    Button d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug);
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


    @OnClick({R.id.buternifeBTN1, R.id.buternifeBTN2, R.id.buternifeBTN3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buternifeBTN1:
                Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buternifeBTN2:
                Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buternifeBTN3:
                Toast.makeText(this, "d", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
