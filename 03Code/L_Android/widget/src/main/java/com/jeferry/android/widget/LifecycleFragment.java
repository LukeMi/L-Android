package com.jeferry.android.widget;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.SharedElementCallback;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.lukemi.android.common.util.Logcat;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LifecycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LifecycleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Logcat.log(this.toString() + "LifecycleFragment: onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logcat.log(this.toString() + "LifecycleFragment: onCreateView");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lifecycle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Logcat.log(this.toString() + "LifecycleFragment: onViewCreated");

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logcat.log(this.toString() + "LifecycleFragment: onActivityCreated");

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Logcat.log(this.toString() + "LifecycleFragment: onViewStateRestored");

        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        Logcat.log(this.toString() + "LifecycleFragment: onStart");

        super.onStart();
    }

    @Override
    public void onResume() {
        Logcat.log(this.toString() + "LifecycleFragment: onResume");

        super.onResume();
    }

    @Override
    public void onPause() {
        Logcat.log(this.toString() + "LifecycleFragment: onPause");

        super.onPause();
    }

    @Override
    public void onStop() {
        Logcat.log(this.toString() + "LifecycleFragment: onStop");

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Logcat.log(this.toString() + "LifecycleFragment: onDestroyView");

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Logcat.log(this.toString() + "LifecycleFragment: onDestroy");

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Logcat.log(this.toString() + "LifecycleFragment: onDetach");


        super.onDetach();
    }

    public LifecycleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LifecycleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LifecycleFragment newInstance(String param1, String param2) {
        LifecycleFragment fragment = new LifecycleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void setArguments(Bundle args) {
        Logcat.log(this.toString() + "LifecycleFragment: setArguments");

        super.setArguments(args);
    }

    @Override
    public void setInitialSavedState(SavedState state) {
        Logcat.log(this.toString() + "LifecycleFragment: setInitialSavedState");

        super.setInitialSavedState(state);
    }

    @Override
    public void setTargetFragment(Fragment fragment, int requestCode) {
        Logcat.log(this.toString() + "LifecycleFragment: setTargetFragment");

        super.setTargetFragment(fragment, requestCode);
    }

    @Override
    public Context getContext() {
        Logcat.log(this.toString() + "LifecycleFragment: getContext");

        return super.getContext();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Logcat.log(this.toString() + "LifecycleFragment: onHiddenChanged");

        super.onHiddenChanged(hidden);
    }

    @Override
    public void setRetainInstance(boolean retain) {
        Logcat.log(this.toString() + "LifecycleFragment: setRetainInstance");

        super.setRetainInstance(retain);
    }

    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        Logcat.log(this.toString() + "LifecycleFragment: setHasOptionsMenu");

        super.setHasOptionsMenu(hasMenu);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        Logcat.log(this.toString() + "LifecycleFragment: setMenuVisibility");

        super.setMenuVisibility(menuVisible);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Logcat.log(this.toString() + "LifecycleFragment: setUserVisibleHint");

        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public boolean getUserVisibleHint() {
        Logcat.log(this.toString() + "LifecycleFragment: getUserVisibleHint");

        return super.getUserVisibleHint();
    }

    @Override
    public LoaderManager getLoaderManager() {
        Logcat.log(this.toString() + "LifecycleFragment: getLoaderManager");

        return super.getLoaderManager();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logcat.log(this.toString() + "LifecycleFragment: onActivityResult");

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Logcat.log(this.toString() + "LifecycleFragment: onRequestPermissionsResult");

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        Logcat.log(this.toString() + "LifecycleFragment: shouldShowRequestPermissionRationale");

        return super.shouldShowRequestPermissionRationale(permission);
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        Logcat.log(this.toString() + "LifecycleFragment: onInflate");

        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        Logcat.log(this.toString() + "LifecycleFragment: onInflate");

        super.onInflate(activity, attrs, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Logcat.log(this.toString() + "LifecycleFragment: onAttach");

        super.onAttach(context);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Logcat.log(this.toString() + "LifecycleFragment: onCreateAnimation");

        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Nullable
    @Override
    public View getView() {
        Logcat.log(this.toString() + "LifecycleFragment: getView");

        return super.getView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Logcat.log(this.toString() + "LifecycleFragment: onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Logcat.log(this.toString() + "LifecycleFragment: onConfigurationChanged");

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        Logcat.log(this.toString() + "LifecycleFragment: onLowMemory");

        super.onLowMemory();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Logcat.log(this.toString() + "LifecycleFragment: onCreateOptionsMenu");

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        Logcat.log(this.toString() + "LifecycleFragment: onPrepareOptionsMenu");

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onDestroyOptionsMenu() {
        Logcat.log(this.toString() + "LifecycleFragment: onDestroyOptionsMenu");

        super.onDestroyOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logcat.log(this.toString() + "LifecycleFragment: onOptionsItemSelected");

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Logcat.log(this.toString() + "LifecycleFragment: onOptionsMenuClosed");

        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Logcat.log(this.toString() + "LifecycleFragment: onCreateContextMenu");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void registerForContextMenu(View view) {
        Logcat.log(this.toString() + "LifecycleFragment: registerForContextMenu");

        super.registerForContextMenu(view);
    }

    @Override
    public void unregisterForContextMenu(View view) {
        Logcat.log(this.toString() + "LifecycleFragment: unregisterForContextMenu");

        super.unregisterForContextMenu(view);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Logcat.log(this.toString() + "LifecycleFragment: onContextItemSelected");

        return super.onContextItemSelected(item);
    }

    @Override
    public void setEnterSharedElementCallback(SharedElementCallback callback) {
        Logcat.log(this.toString() + "LifecycleFragment: setEnterSharedElementCallback");

        super.setEnterSharedElementCallback(callback);
    }

    @Override
    public void setExitSharedElementCallback(SharedElementCallback callback) {
        Logcat.log(this.toString() + "LifecycleFragment: setExitSharedElementCallback");

        super.setExitSharedElementCallback(callback);
    }

    @Override
    public void setEnterTransition(Object transition) {
        Logcat.log(this.toString() + "LifecycleFragment: setEnterTransition");

        super.setEnterTransition(transition);
    }

    @Override
    public Object getEnterTransition() {
        Logcat.log(this.toString() + "LifecycleFragment: getEnterTransition");

        return super.getEnterTransition();
    }

    @Override
    public void setReturnTransition(Object transition) {
        Logcat.log(this.toString() + "LifecycleFragment: setReturnTransition");

        super.setReturnTransition(transition);
    }

    @Override
    public Object getReturnTransition() {
        Logcat.log(this.toString() + "LifecycleFragment: getReturnTransition");

        return super.getReturnTransition();
    }

    @Override
    public void setExitTransition(Object transition) {
        Logcat.log(this.toString() + "LifecycleFragment: setExitTransition");

        super.setExitTransition(transition);
    }

    @Override
    public Object getExitTransition() {
        Logcat.log(this.toString() + "LifecycleFragment: getExitTransition");

        return super.getExitTransition();
    }

    @Override
    public void setReenterTransition(Object transition) {
        Logcat.log(this.toString() + "LifecycleFragment: setReenterTransition");

        super.setReenterTransition(transition);
    }

    @Override
    public Object getReenterTransition() {
        Logcat.log(this.toString() + "LifecycleFragment: getReenterTransition");

        return super.getReenterTransition();
    }

    @Override
    public void setSharedElementEnterTransition(Object transition) {
        Logcat.log(this.toString() + "LifecycleFragment: setSharedElementEnterTransition");

        super.setSharedElementEnterTransition(transition);
    }

    @Override
    public Object getSharedElementEnterTransition() {
        Logcat.log(this.toString() + "LifecycleFragment: getSharedElementEnterTransition");

        return super.getSharedElementEnterTransition();
    }

    @Override
    public void setSharedElementReturnTransition(Object transition) {
        Logcat.log(this.toString() + "LifecycleFragment: setSharedElementReturnTransition");

        super.setSharedElementReturnTransition(transition);
    }

    @Override
    public Object getSharedElementReturnTransition() {
        Logcat.log(this.toString() + "LifecycleFragment: getSharedElementReturnTransition");

        return super.getSharedElementReturnTransition();
    }

    @Override
    public void setAllowEnterTransitionOverlap(boolean allow) {
        Logcat.log(this.toString() + "LifecycleFragment: setAllowEnterTransitionOverlap");

        super.setAllowEnterTransitionOverlap(allow);
    }

    @Override
    public boolean getAllowEnterTransitionOverlap() {
        Logcat.log(this.toString() + "LifecycleFragment: getAllowEnterTransitionOverlap");

        return super.getAllowEnterTransitionOverlap();
    }

    @Override
    public void setAllowReturnTransitionOverlap(boolean allow) {
        Logcat.log(this.toString() + "LifecycleFragment: setAllowReturnTransitionOverlap");

        super.setAllowReturnTransitionOverlap(allow);
    }

    @Override
    public boolean getAllowReturnTransitionOverlap() {
        Logcat.log(this.toString() + "LifecycleFragment: getAllowReturnTransitionOverlap");

        return super.getAllowReturnTransitionOverlap();
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        Logcat.log(this.toString() + "LifecycleFragment: dump");

        super.dump(prefix, fd, writer, args);
    }
}
