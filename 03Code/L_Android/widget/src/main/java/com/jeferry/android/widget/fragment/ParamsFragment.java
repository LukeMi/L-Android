package com.jeferry.android.widget.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeferry.android.widget.R;

public class ParamsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";


    private int page;

    public static ParamsFragment newInstance(int page) {
        ParamsFragment fragment = new ParamsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_params, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ((TextView) view.findViewById(R.id.tv_page)).setText(String.valueOf(page));
    }
}
