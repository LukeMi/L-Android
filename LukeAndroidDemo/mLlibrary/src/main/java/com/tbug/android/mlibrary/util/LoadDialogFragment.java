package com.tbug.android.mlibrary.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mzchen on 2017/2/20.
 */

public class LoadDialogFragment extends DialogFragment {

    private Context context;
    private String Message;

    public LoadDialogFragment() {
    }

    /*public LoadDialogFragment(Context context) {
        this.context = context;
    }*/


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
