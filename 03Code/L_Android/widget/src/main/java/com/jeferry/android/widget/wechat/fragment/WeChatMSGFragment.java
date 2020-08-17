package com.jeferry.android.widget.wechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.jeferry.android.widget.R;
import com.lukemi.android.common.base.BaseLazyFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link WeChatMSGFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeChatMSGFragment extends BaseLazyFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ViewFlipper viewflipper;

    private String mParam1;
    private String mParam2;

    private LayoutInflater inflater;


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
        View view = inflater.inflate(R.layout.fragment_we_chat_msg, container, false);
        this.inflater = inflater;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewflipper = view.findViewById(R.id.viewflipper);
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public WeChatMSGFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeChatMSGFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeChatMSGFragment newInstance(String param1, String param2) {
        WeChatMSGFragment fragment = new WeChatMSGFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        View c1 = inflater.inflate(R.layout.item_common_image, null);
        ImageView img1 = c1.findViewById(R.id.img_common);
        img1.setImageResource(R.drawable.wallpaper1);
        View c2 = inflater.inflate(R.layout.item_common_image, null);
        ImageView img2 = c2.findViewById(R.id.img_common);
        img2.setImageResource(R.drawable.wallpaper2);
        View c3 = inflater.inflate(R.layout.item_common_image, null);
        ImageView img3 = c3.findViewById(R.id.img_common);
        img3.setImageResource(R.drawable.wallpaper3);
        View c4 = inflater.inflate(R.layout.item_common_image, null);
        ImageView img4 = c4.findViewById(R.id.img_common);
        img4.setImageResource(R.drawable.wallpaper4);
        viewflipper.addView(c1);
        viewflipper.addView(c2);
        viewflipper.addView(c3);
        viewflipper.addView(c4);
        viewflipper.startFlipping();

    }


}
