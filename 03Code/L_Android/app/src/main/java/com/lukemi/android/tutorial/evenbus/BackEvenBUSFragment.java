package com.lukemi.android.tutorial.evenbus;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.evenbus.ForgroundActivity;
import com.lukemi.android.tutorial.glide.GlideUtil;
import com.lukemi.android.tutorial.util.CommonUtils;
import com.lukemi.android.tutorial.util.HttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BackEvenBUSFragment extends Fragment {
    private View view;
    private ImageView img;
    private TextView text;
    private Button btn;

    public static BackEvenBUSFragment newInstance() {
        BackEvenBUSFragment fragment = new BackEvenBUSFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_back_even_bu, container, false);
        text = view.findViewById(R.id.text_bd);
        img = view.findViewById(R.id.img_bd);
        btn = view.findViewById(R.id.btn_click);
        btn.setOnClickListener((View view) -> {
            startActivity(new Intent(getContext(), ForgroundActivity.class));
        });
        return view;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        text.setText(event);
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        CommonUtils.glideLoadPic(getContext(), url, img);
        GlideUtil.loadImgByUrl(getContext(),img,url);
    }

    ;
}
