package com.lukemi.myandroid.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lukemi.myandroid.viewtest.DialogUtilActivity;

public class WidgetActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_widget);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        findViewById(com.lukemi.myandroid.R.id.TextView).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.EditText).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.Button).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ImageButton).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.CheckBox).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.RadioGroup).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.Toast).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.Spinner).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ListView).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.TabHost).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.Menu).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.AutoCompleteTextView).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.DatePicker).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.Timepicker).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.Dialog).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ImageView).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.Gallery).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ImageSwitcher).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.GridView).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ProgressBar).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.SeekBar).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.RatingBar).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ProgressDialog).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.Notification).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.WebviewActivity).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.TableLayoutActivity).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.WebviewDownloadActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?> targetClass = null;
        switch (v.getId()){
            case com.lukemi.myandroid.R.id.TextView:
                targetClass = TextViewActivity.class;
                break;
            case com.lukemi.myandroid.R.id.EditText:
                targetClass = EditTextActivity.class;
                break;
            case com.lukemi.myandroid.R.id.Button:
                break;
            case com.lukemi.myandroid.R.id.ImageButton:
                targetClass = ImageViewActivity.class;
                break;
            case com.lukemi.myandroid.R.id.CheckBox:
                break;
            case com.lukemi.myandroid.R.id.RadioGroup:
                break;
            case com.lukemi.myandroid.R.id.Toast:
                break;
            case com.lukemi.myandroid.R.id.Spinner:
                break;
            case com.lukemi.myandroid.R.id.ListView:
                targetClass = ListViewActivity.class;
                break;
            case com.lukemi.myandroid.R.id.TabHost:
                break;
            case com.lukemi.myandroid.R.id.Menu:
                break;
            case com.lukemi.myandroid.R.id.AutoCompleteTextView:
                break;
            case com.lukemi.myandroid.R.id.DatePicker:
                break;
            case com.lukemi.myandroid.R.id.Timepicker:
                break;
            case com.lukemi.myandroid.R.id.Dialog:
                targetClass = DialogUtilActivity.class;
                break;
            case com.lukemi.myandroid.R.id.ImageView:
                break;
            case com.lukemi.myandroid.R.id.Gallery:
                break;
            case com.lukemi.myandroid.R.id.ImageSwitcher:
                break;
            case com.lukemi.myandroid.R.id.GridView:
                break;
            case com.lukemi.myandroid.R.id.ProgressBar:
                break;
            case com.lukemi.myandroid.R.id.SeekBar:
                break;
            case com.lukemi.myandroid.R.id.RatingBar:
                break;
            case com.lukemi.myandroid.R.id.ProgressDialog:
                break;
            case com.lukemi.myandroid.R.id.Notification:
                break;
            case com.lukemi.myandroid.R.id.WebviewActivity:
                targetClass = WebviewActivity.class;
                break;
            case com.lukemi.myandroid.R.id.TableLayoutActivity:
                targetClass = TableLayoutActivity.class;
                break;
            case com.lukemi.myandroid.R.id.WebviewDownloadActivity:
                targetClass = WebviewDownloadActivity.class;
                break;

            default:
                Toast.makeText(WidgetActivity.this,"默认的点击",Toast.LENGTH_SHORT).show();
                break;
        }
        if (targetClass != null){
            startActivity(new Intent(WidgetActivity.this,targetClass));
        }
    }
}
