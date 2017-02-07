package com.tbug.android.demo.getappchannelactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tbug.android.demo.R;
import com.tbug.android.demo.app.MyApplication;

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
        getAppchannel_GetAppBTN = ((Button) findViewById(R.id.getAppchannel_GetApp));
        getAppchannel_GetAppBTN.setOnClickListener(this);
        showAppChannel_GetAppET = ((TextView) findViewById(R.id.showAppChannel_GetApp));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.getAppchannel_GetApp:
                String appchannel = ((MyApplication) getApplication()).getAppchannel();
                showAppChannel_GetAppET.setText(appchannel);
                break;
            default:
                Toast.makeText(this, "点击正确的View", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
