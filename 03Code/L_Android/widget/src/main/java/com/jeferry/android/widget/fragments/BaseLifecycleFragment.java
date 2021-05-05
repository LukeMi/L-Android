package com.jeferry.android.widget.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.lukemi.android.common.base.BaseLazyFragment;


/**
 * Created by android on 2017/8/10.
 */

public class BaseLifecycleFragment extends BaseLazyFragment {

    @Override
    public void onAttach(Context context) {
//        Logcat.log(this.getClass().getSimpleName() + "----> onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        Logcat.log(this.getClass().getSimpleName() + "----> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Logcat.log(this.getClass().getSimpleName() + "----> onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        Logcat.log(this.getClass().getSimpleName() + "----> onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        Logcat.log(this.getClass().getSimpleName() + "----> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
//        Logcat.log(this.getClass().getSimpleName() + "----> onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
//        Logcat.log(this.getClass().getSimpleName() + "----> onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
//        Logcat.log(this.getClass().getSimpleName() + "----> onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
//        Logcat.log(this.getClass().getSimpleName() + "----> onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
//        Logcat.log(this.getClass().getSimpleName() + "----> onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
//        Logcat.log(this.getClass().getSimpleName() + "----> onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
//        Logcat.log(this.getClass().getSimpleName() + "----> onDetach");
        super.onDetach();
    }

}
