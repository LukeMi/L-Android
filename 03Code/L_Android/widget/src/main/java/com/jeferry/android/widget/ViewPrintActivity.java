package com.jeferry.android.widget;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.ShowBigPicClass;

/**
 * View 截图
 */
public class ViewPrintActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout frameLayout;
    private Button screenShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_print);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        frameLayout = findViewById(R.id.frameLayout);
        screenShot = findViewById(R.id.screenShot);
        screenShot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.screenShot) {
            Bitmap viewBitmap = getViewBitmap(frameLayout);
            ShowBigPicClass sbpc = new ShowBigPicClass(this, viewBitmap);
            sbpc.showDetailPhoto();
        }
    }

    /**
     * 将View 转化成 BitMap
     *
     * @param addViewContent
     * @return
     */
    private Bitmap getViewBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);

        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());

        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }

}
