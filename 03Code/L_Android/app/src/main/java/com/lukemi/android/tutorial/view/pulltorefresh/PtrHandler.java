package com.lukemi.android.tutorial.view.pulltorefresh;

public interface PtrHandler {

    /**
     * When refresh begin
     *
     * @param frame
     */
    void onRefreshBegin(final PtrFrameLayout frame);
}