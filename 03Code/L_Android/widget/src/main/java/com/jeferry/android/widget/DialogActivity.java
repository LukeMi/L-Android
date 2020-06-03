package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jeferry.android.widget.dialog.BaseDialogFragment;
import com.lukemi.android.tutorial.view.ActionSheetDialog;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.center_dlg).setOnClickListener(this::onViewClicked);
        findViewById(R.id.btn_ios).setOnClickListener(this::onViewClicked);
    }


    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_ios) {
            showDlg();
        } else if (view.getId() == R.id.center_dlg) {
            BaseDialogFragment.newInstance("title","msg").show(getSupportFragmentManager(),"BaseDialogFragment");
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
}
