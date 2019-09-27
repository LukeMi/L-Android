package com.jeferry.android.widget.wechat;

import android.app.AlertDialog;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeferry.android.widget.R;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private AlertDialog dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        findViewById(R.id.btn_comment).setOnClickListener(this::onViewClicked);

    }


    public void onViewClicked(View v) {
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
        int id = v.getId();
        if (id == R.id.text_cancel) {
            dlg.dismiss();
            dlg = null;
        } else if (id == R.id.text_sent) {
            dlg.dismiss();
            dlg = null;
        }
    }
}
