package com.lukemi.android.tutorial.getappchannelactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.app.Application;

public class GetAppchannelActivity extends AppCompatActivity implements View.OnClickListener{

    private Button getAppchannel_GetAppBTN;
    private TextView showAppChannel_GetAppET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_appchannel);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        getAppchannel_GetAppBTN = findViewById(R.id.getAppchannel_GetApp);
        getAppchannel_GetAppBTN.setOnClickListener(this);
        showAppChannel_GetAppET = findViewById(R.id.showAppChannel_GetApp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.getAppchannel_GetApp:
                String appchannel = ((Application) getApplication()).getAppChannel();
                showAppChannel_GetAppET.setText(appchannel);
                break;
            default:
                Toast.makeText(this, "点击正确的View", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
