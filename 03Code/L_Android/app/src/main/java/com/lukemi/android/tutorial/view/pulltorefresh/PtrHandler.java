package com.lukemi.android.tutorial.view.pulltorefresh;

public interface PtrHandler {

    /**
     * When refresh begin
     *
     * @param frame
     */
    public void onRefreshBegin(final PtrFrameLayout frame);
}