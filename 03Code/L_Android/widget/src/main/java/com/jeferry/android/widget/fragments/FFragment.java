package com.jeferry.android.widget.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeferry.android.widget.R;
import com.lukemi.android.common.util.Logcat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FFragment extends BaseLifecycleFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FFragment newInstance(String param1, String param2) {
        FFragment fragment = new FFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logcat.log(this.getClass().getSimpleName() + "----> onCreateView");
        return inflater.inflate(R.layout.fragment_f, container, false);
    }

}
