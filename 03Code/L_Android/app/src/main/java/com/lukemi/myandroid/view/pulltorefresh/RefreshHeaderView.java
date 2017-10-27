package com.lukemi.myandroid.view.pulltorefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lukemi.myandroid.R;


/**
 * Created by admin on 2017/2/23.
 */
public class RefreshHeaderView extends PtrFrameLayout implements PtrUIHandler {


    private TextView status_text;
    private ImageView ren;
    private int viewHeight;
    private ProgressBar donghua;
    private RefreshDistanceListener listener;

    /**
     * 自开始下拉 0.2倍height内是否执行了缩放，
     */
    public void setOnRefreshDistanceListener(RefreshDistanceListener listener) {
        this.listener = listener;
    }

    public RefreshHeaderView(Context context) {
        super(context);
        initView();
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

        View view = View.inflate(this.getContext(), R.layout.jingdongheaderviewlayout, null);
        status_text = (TextView) view.findViewById(R.id.status_test);
        ren = (ImageView) view.findViewById(R.id.ren);
        donghua = (ProgressBar) view.findViewById(R.id.donghua);
        setRatioOfHeaderHeightToRefresh(1.0f);
        setHeaderView(view);
        addPtrUIHandler(this);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
//        drawable.stop();
        donghua.setVisibility(View.GONE);
        ren.setVisibility(View.VISIBLE);
//        ren.startAnimation(getRotateAnimation(0, 90));
    }

/*    private RotateAnimation getRotateAnimation(float fromDegrees, float toDegrees) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees, 0.5f, 0.5f);
        rotateAnimation.setDuration(200);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setFillAfter(true);
        return rotateAnimation;
    }*/

    ;

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

        if (frame.isPullToRefresh()) {
            status_text.setText(R.string.release_to_refresh);
//            ren.startAnimation(getRotateAnimation(0, -90));
        } else {
            status_text.setText(R.string.pull_to_refresh);
//            ren.startAnimation(getRotateAnimation(0, 90));
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        ren.setVisibility(View.GONE);
        donghua.setVisibility(View.VISIBLE);
        status_text.setText(R.string.refreshing);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
    }

    private static final String TAG = "JDHeaderView";

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //ptrIndicator.setRatioOfHeaderHeightToRefresh(1.0f);
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
        if (listener != null) {
            listener.onPositionChange(currentPos);
        }
        if (viewHeight == 0)
            viewHeight = ptrIndicator.getHeaderHeight();
        float v = currentPos * 1.0f / viewHeight;
        if (v > 1) v = 1;
        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                status_text.setText(R.string.pull_to_refresh);
//                ren.startAnimation(getRotateAnimation(0, 90));
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                ren.startAnimation(getRotateAnimation(0, -90));
                status_text.setText(R.string.release_to_refresh);
            }
        }

    }

    public interface RefreshDistanceListener {
        void onPositionChange(int currentPosY);
    }

}
