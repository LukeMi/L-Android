package com.lukemi.android.tutorial.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lukemi.android.common.util.Logcat;

/**
 * Created by android on 2017/8/8.
 */

public class BaseLazyFragment extends Fragment {

    private boolean isFirstView = true;

    @Override
    public void onAttach(Context context) {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onDetach");
        super.onDetach();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logcat.log( this.getClass().getSimpleName() + "----->" + "setUserVisibleHint ---->" + isVisibleToUser);
        if (getUserVisibleHint()) {
            onLazyLoad();
        }
    }

    protected void onLazyLoad() {
        Logcat.log( this.getClass().getSimpleName() + "----->" + "onLazyLoad");
    }
}
