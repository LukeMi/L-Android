package com.jeferry.android.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

public class GlobalDialogActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void start(Context context) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.dd_mask_in, R.anim.dd_mask_out);
        Intent intent = new Intent(context, GlobalDialogActivity.class);
        context.startActivity(intent, options.toBundle());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.dd_mask_in, R.anim.dd_mask_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // android O fix bug orientation
        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_global);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels * 0.8);
        getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setWindowAnimations(R.style.AnimMaskIn);
    }
}