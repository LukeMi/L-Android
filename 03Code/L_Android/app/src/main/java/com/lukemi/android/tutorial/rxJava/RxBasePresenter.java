package com.lukemi.android.tutorial.rxJava;

import java.lang.ref.WeakReference;

public class RxBasePresenter<T> {

    private WeakReference<T> tWeakReference;
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    /**
     * 绑定视图
     *
     * @param view
     */
    public void onAttach(T view) {
        tWeakReference = new WeakReference<T>(view);
        t = view;
    }

    /**
     * 解除视图
     */
    public void onDetach() {
        if (tWeakReference != null) {
            tWeakReference.clear();
            tWeakReference = null;
        }

    }
}
