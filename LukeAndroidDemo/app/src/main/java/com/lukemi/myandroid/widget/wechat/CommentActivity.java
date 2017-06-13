package com.lukemi.myandroid.widget.wechat;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.myandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private AlertDialog dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_comment)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.view_comment, null);
        view.findViewById(R.id.text_cancel).setOnClickListener(this);
        view.findViewById(R.id.text_sent).setOnClickListener(this);
        builder.setView(view);
        dlg = builder.create();
        dlg.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_cancel:
                dlg.dismiss();
                dlg = null;
                break;
            case R.id.text_sent:
                dlg.dismiss();
                dlg = null;
                break;
            default:
                break;
        }
    }
}
