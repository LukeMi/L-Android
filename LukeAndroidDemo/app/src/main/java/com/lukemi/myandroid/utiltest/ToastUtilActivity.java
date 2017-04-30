package com.lukemi.myandroid.utiltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lukemi.myandroid.util.ToastUtil;

import java.lang.reflect.Field;

public class ToastUtilActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_toast_util);
        initView();
    }

    private void initView() {
        findViewById(com.lukemi.myandroid.R.id.show_makeText_String).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.show_makeText_StringID).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.show_makeText_position).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.show_makeText_position_StringID).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.show_view).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.show_view_position).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.lukemi.myandroid.R.id.show_makeText_String:
                ToastUtil.show_makeText(this, "一般性Toast显示+String", Toast.LENGTH_SHORT);
                break;
            case com.lukemi.myandroid.R.id.show_makeText_StringID:
//                int resourceID = getResources().getIdentifier("toast_normal", "string", this.getPackageName());
                int resourceID = getResource("toast_normal");
                ToastUtil.show_makeText(this, resourceID, Toast.LENGTH_SHORT);
                break;
            case com.lukemi.myandroid.R.id.show_makeText_position:
                ToastUtil.show_makeText_position(this, "一般性Toast显示+String", Gravity.CENTER, 0, 0, Toast.LENGTH_SHORT);
                break;
            case com.lukemi.myandroid.R.id.show_makeText_position_StringID:
                ToastUtil.show_makeText_position(this, com.lukemi.myandroid.R.string.toast_normal, Gravity.CENTER, 0, 0, Toast.LENGTH_SHORT);
                break;
            case com.lukemi.myandroid.R.id.show_view:
                ImageView iv = new ImageView(this);
                iv.setImageResource(com.lukemi.myandroid.R.drawable.pageload_icon2);
                ToastUtil tu = new ToastUtil();
                tu.show_view(this, iv, 0, 0, Toast.LENGTH_SHORT);
                break;
            case com.lukemi.myandroid.R.id.show_view_position:
                ImageView iv1 = new ImageView(this);
                iv1.setImageResource(com.lukemi.myandroid.R.drawable.pageload_icon2);
                ToastUtil tu1 = new ToastUtil();
                tu1.show_view_position(this, iv1, 0, 0, Gravity.CENTER, 0, 0, Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }
    }

    public int getResource(String stringName) {
        Class string = com.lukemi.myandroid.R.string.class;
        try {
            Field field = string.getField(stringName);
            int resId = field.getInt(stringName);
            return resId;
        } catch (Exception e) {
            return 0;
        }
    }
}
