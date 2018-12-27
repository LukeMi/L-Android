package com.lukemi.android.tutorial.clip;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ClipActivity extends AbsBaseActivity {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_content_show)
    TextView tvContentShow;
    private ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_clip;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        clipboardManager = ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE));
    }

    @OnClick({R.id.btn_copy, R.id.btn_paste, R.id.btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_copy:
                copy();
                break;
            case R.id.btn_paste:
                paste();
                break;
            case R.id.btn_clear:
                clear();
                break;
        }
    }

    private void copy() {
        ClipData clipData = ClipData.newPlainText("Source Text", etContent.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
    }

    private void paste() {
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        if (primaryClip != null) {
            int itemCount = primaryClip.getItemCount();
            if (itemCount > 0) {
                ClipData.Item itemAt = primaryClip.getItemAt(0);
                String s = itemAt.getText().toString();
                tvContentShow.setText(s);
            }
        }
    }

    private void clear() {
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        int itemCount = primaryClip.getItemCount();
        if (itemCount > 0) {
            tvContentShow.setText("");
            clipboardManager.setPrimaryClip(ClipData.newPlainText("", ""));
            Snackbar.make(tvContentShow, "clear clipboarder ", Snackbar.LENGTH_SHORT).show();
        }
    }
}
