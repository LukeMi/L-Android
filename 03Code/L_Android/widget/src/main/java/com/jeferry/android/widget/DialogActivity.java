package com.jeferry.android.widget;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.jeferry.android.widget.dialog.BaseDialogFragment;
import com.lukemi.android.common.util.DialogUtil;
import com.lukemi.android.common.view.ActionSheetDialog;

public class DialogActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.center_dlg).setOnClickListener(this::onClick);
        findViewById(R.id.btn_ios).setOnClickListener(this::onClick);
        findViewById(R.id.btn_global).setOnClickListener(this::onClick);
        findViewById(R.id.btn_progress).setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ios) {
            showDlg();
        } else if (id == R.id.center_dlg) {
            BaseDialogFragment.newInstance("title", "msg").show(getSupportFragmentManager(), "BaseDialogFragment");
        } else if (id == R.id.btn_global) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                GlobalDialogActivity.start(this);
            }
        } else if (id == R.id.btn_progress) {
            showDlgProgress();
        }
    }

    private void showDlg() {
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("发送给好友", ActionSheetDialog.SheetItemColor.Blue,
                        which -> Toast.makeText(DialogActivity.this, "发送给好友", Toast.LENGTH_SHORT).show())
                .addSheetItem("转载到空间相册", ActionSheetDialog.SheetItemColor.Blue,
                        which -> Toast.makeText(DialogActivity.this, "发送给好友", Toast.LENGTH_SHORT).show())
                .addSheetItem("上传到群相册", ActionSheetDialog.SheetItemColor.Blue,
                        which -> Toast.makeText(DialogActivity.this, "发送给好友", Toast.LENGTH_SHORT).show())
                .addSheetItem("保存到手机", ActionSheetDialog.SheetItemColor.Blue,
                        which -> Toast.makeText(DialogActivity.this, "发送给好友", Toast.LENGTH_SHORT).show())
                .addSheetItem("收藏", ActionSheetDialog.SheetItemColor.Blue,
                        which -> Toast.makeText(DialogActivity.this, "发送给好友", Toast.LENGTH_SHORT).show())
                .addSheetItem("查看聊天图片", ActionSheetDialog.SheetItemColor.Blue,
                        which -> Toast.makeText(DialogActivity.this, "发送给好友", Toast.LENGTH_SHORT).show()).show();
    }

    private void showDlgProgress() {
        progressDialog = DialogUtil.show_progressDialog(this, 0, null, "进度条对话框...", false, true);
        progressDialog.setOnKeyListener((dialog, keyCode, event) -> {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    progressDialog.setMessage("你点了返回键...");
                    break;
            }
            return false;
        });
    }
}
