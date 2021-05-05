package com.lukemi.android.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * 坐标系
 */
public class CoordinateView extends View {

    private int xCoorColor = Color.BLACK;
    private int yCoorColor = Color.BLACK;

    private int xCoorWidth = 5;
    private int yCoorWidth = 5;

    private int xTextColor = Color.BLACK;
    private int yTextColor = Color.BLACK;

    private int cx = 0;
    private int cy = 0;


    private List<DataBean> dataBeanList;

    public CoordinateView(Context context) {
        super(context);
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setDataBeanList(List<DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawX(canvas);
        drawY(canvas);
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        Paint paint = initLinePaint();
        Path path = new Path();
        if (dataBeanList!= null && !dataBeanList.isEmpty()) {
            for (int i = 0; i < dataBeanList.size(); i++) {
                DataBean dataBean = dataBeanList.get(i);
                float x = dataBean.getX()+50;
                float y = dataBean.getY();
                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
        }
        canvas.drawPath(path, paint);
    }

    private Paint initLinePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(xCoorColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(xCoorWidth);
        return paint;
    }


    private void drawX(Canvas canvas) {
        Paint paint = initXPaint();
        int width = getWidth();

        int height = getHeight();

        Path path = new Path();
        path.moveTo(50, height);
        path.lineTo(50, 50);

        canvas.drawPath(path, paint);
    }

    private Paint initXPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(xCoorColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(xCoorWidth);
        return paint;
    }

    private void drawY(Canvas canvas) {
        Paint paint = initYPaint();
        int height = getHeight();
        int width = getWidth();

        Path path = new Path();
        path.moveTo(0, height - 50);
        path.lineTo(width - 50, height - 50);

        canvas.drawPath(path, paint);
    }

    private Paint initYPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(yCoorColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(yCoorWidth);
        return paint;
    }


}
