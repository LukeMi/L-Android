package com.lukemi.android.common.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by android on 2017/8/28.
 */

public class SingleLineLinearlayout extends LinearLayout {

    private OnSinglineAddCompleteListener listener;

    private View child;

    /**
     * 布局中子控件设置的LayoutParam
     */
    private MarginLayoutParams layoutParams;
    private int lineWidth;
    private int lineHeight;

    private int height;
    private int width;

    private List<View> views = new ArrayList<>();

    public void setOnSinglineAddCompleteListener(OnSinglineAddCompleteListener listener) {
        this.listener = listener;
    }

    public SingleLineLinearlayout(Context context) {
        super(context);
        init();
    }

    public SingleLineLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SingleLineLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SingleLineLinearlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //根据所有子控件设置自己的宽和高
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            // 让系统去测量当前child的宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 获取当前child实际占据的宽高
            layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            //如果加入当前child后超出最大允许宽度，则将目前最大宽度给width，累加height，然后开启新行
            if (lineWidth + childWidth > MeasureSpec.getSize(widthMeasureSpec)) {
                listener.onSinglineComplite(i);
                width = Math.max(lineWidth, childWidth);// 对比得到最大宽度
                removeView(child);
                break;
            } else {
                // 否则（不换行）累加行宽，lineHeight取最大高度
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
                views.add(child);
            }
            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较，并累加行高
            if (i == getChildCount() - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        //如果是布局中设置的是wrap_content，设置为我们计算的值；否则，直接设置为父容器测量的值。
        width = (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) ? MeasureSpec.getSize(widthMeasureSpec) : width;
        height = (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) ? MeasureSpec.getSize(heightMeasureSpec) : height;
        setMeasuredDimension(width, height);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        views.clear();
        lineWidth = 0;
        lineHeight = 0;
        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            // 如果需要换行
            if (childWidth + layoutParams.leftMargin + layoutParams.rightMargin + lineWidth > width) {
                break;
            }
            //如果不需要换行，则累加
            lineWidth += childWidth + layoutParams.leftMargin + layoutParams.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + layoutParams.topMargin + layoutParams.bottomMargin);
            lineViews.add(child);
        }
        //记录当前child相对前一个child的坐标位置
        int left = 0;
        int top = 0;

        for (int j = 0; j < lineViews.size(); j++) {
            child = lineViews.get(j);
            if (child.getVisibility() == View.GONE) continue;
            layoutParams = (MarginLayoutParams) child.getLayoutParams();
            //计算child的坐标
            int leftPosition = left + layoutParams.leftMargin;
            int topPosition = top + layoutParams.topMargin;
            int rightPosition = leftPosition + child.getMeasuredWidth();
            int bottomPosition = topPosition + child.getMeasuredHeight();
            //对child进行布局
            child.layout(leftPosition, topPosition, rightPosition, bottomPosition);
            //相对位置右移
            left = rightPosition + layoutParams.rightMargin;
        }
        //相对位置从左侧重头开始，并下移
        requestLayout();
    }

    public interface OnSinglineAddCompleteListener {
        void onSinglineComplite(int childCount);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        int measuredWidth = child.getMeasuredWidth();
        int widthSum = 0;
        requestLayout();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            int width = layoutParams.width;
            widthSum += width;
        }
    }
}
