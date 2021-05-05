package com.lukemi.android.tutorial.list_r.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lukemi.android.tutorial.R;


/**
 * 分割线ItemDecoration
 * Created by chenmz
 * on 2018/1/11 .
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;
    private Paint mPaint;
    private int dividerColor;//分割线的颜色
    private int dividerHeight = 0;//分割线的高度

    public DividerItemDecoration(@NonNull Context context) {
        this.context = context;
        dividerColor = context.getResources().getColor(R.color.mediumaquamarine);
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.dp5);
        initPaint();
    }

    public DividerItemDecoration(@NonNull Context context, @ColorInt int dividerColor, @IntRange int dividerHeight) {
        this.context = context;
        this.dividerColor = dividerColor;
        this.dividerHeight = dividerHeight;
        initPaint();
    }

    /**
     * 构建者模式
     */
    public static class Builder {

        /**
         * 分割线的位置
         */
        enum DividerLocation {
            LEFT,//分割线在左侧
            TOP,
            RIGHT,
            BOTTOM
        }

        private int dividerColor = Color.argb(255, 255, 255, 255);//分割线的颜色
        private int dividerHeight = 5;//分割线的高度
        private DividerLocation dividerLocation = DividerLocation.BOTTOM;
        private static Context context;

        public Builder(Context context) {
            DividerItemDecoration.Builder.context = context;
        }


        /**
         * 设置分割线位置  Todo 这个暂未完成
         *
         * @param dividerLocation
         * @return
         */
        public Builder setDividerLocation(DividerLocation dividerLocation) {
            this.dividerLocation = dividerLocation;
            return this;

        }

        /**
         * 设置分格线的颜色
         *
         * @param colorRes 颜色资源ID
         */
        public Builder setDividerColorRes(@ColorRes int colorRes) {
            if (context != null) {
                dividerColor = context.getResources().getColor(colorRes);
            } else {
                new RuntimeException("context can not be null");
            }
            return this;
        }

        /**
         * 设置分格线的颜色
         *
         * @param colorInt 颜色整数值
         */
        public Builder setDividerColorInt(@ColorInt int colorInt) {
            dividerColor = colorInt;
            return this;
        }

        /**
         * 设置分割线高度
         *
         * @param dividerHeightRes dimen 资源ID
         */
        public Builder setDividerHeightRes(@DimenRes int dividerHeightRes) {
            if (context != null) {
                this.dividerHeight = context.getResources().getDimensionPixelSize(dividerHeightRes);
            } else {
                new RuntimeException("context can not be null");
            }
            return this;
        }

        /**
         * 设置分割线高度
         *
         * @param dividerHeightInt 高度值整数
         */
        public Builder setDividerHeightInt(@IntRange int dividerHeightInt) {
            this.dividerHeight = dividerHeightInt;
            return this;
        }

        /**
         * 创建Decoration
         */
        public DividerItemDecoration create() {
            return new DividerItemDecoration(context, dividerColor, dividerHeight);
        }

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(dividerColor);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;

    }

    /**
     * 我的理解
     *
     * @param c      RecyclerView/ #parent 的画布 ，
     * @param parent 当前的 RecyclerView
     * @param state  状态
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i % 2 == 0) {
                View view = parent.getChildAt(i);// view itemView
                int top = view.getBottom();
                int bottom = top + dividerHeight;
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }


}
