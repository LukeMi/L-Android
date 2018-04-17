package com.lukemi.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lukemi.common.R;


/**
 * 圆弧控件
 * Created by chenmz
 * on 2017/11/10 0010.
 */

public class CirculaArcView extends View {

    private int ca_edge_color;
    private int ca_inner_color;
    private float ca_edge_wide;
    private float ca_radio;
    private Paint innerPaint;
    private int alpha = 250;

    public CirculaArcView(Context context) {
        super(context);
    }

    public CirculaArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //def
        int def_ca_edge_color = getResources().getColor(R.color.def_ca_edge_color);
        int def_ca_inner_color = getResources().getColor(R.color.def_ca_inner_color);
        float def_ca_edge_wide = getResources().getDimension(R.dimen.def_ca_edge_wide);
        float def_ca_radio = getResources().getDimension(R.dimen.def_ca_radio);

        //取属性值
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircularArcView);
        ca_edge_color = array.getColor(R.styleable.CircularArcView_ca_edge_color, def_ca_edge_color);
        ca_inner_color = array.getColor(R.styleable.CircularArcView_ca_inner_color, def_ca_inner_color);
        ca_edge_wide = array.getDimension(R.styleable.CircularArcView_ca_edge_wide, def_ca_edge_wide);
        ca_radio = array.getDimension(R.styleable.CircularArcView_ca_radio, def_ca_radio);
        array.recycle();


        innerPaint = getInnerPaint();
    }

    //获取画笔
    private Paint getOutPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ca_edge_color);
        return paint;
    }

    //获取画笔
    private Paint getInnerPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ca_edge_color);
        paint.setAlpha(alpha);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ca_edge_wide);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        float cx = width / 2;
        float cy = height / 2;
        canvas.drawCircle(cx, cy, ca_radio, innerPaint);
    }

    public void setCa_radio(int radio) {
        ca_radio = radio;
        this.invalidate();
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
        if (innerPaint != null) {
            innerPaint.setAlpha(this.alpha);
        }
    }
}
