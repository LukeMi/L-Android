package com.jeferry.android.widget.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
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
        int textSize = (int) ((Math.random() * 10)) + this.page;
        int textColor = Color.parseColor("#ff0000") + (int) (Math.random() * 100);
        TextView page = (TextView) view.findViewById(R.id.tv_page);
        page.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        page.setTextColor(textColor);
        page.setText(String.valueOf(this.page));
    }
}
