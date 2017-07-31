package com.lukemi.myandroid.widget.wechat.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.lukemi.myandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeChatMSGFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeChatMSGFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.viewflipper)
    ViewFlipper viewflipper;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private LayoutInflater inflater;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_we_chat_msg, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.inflater = inflater;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View c1 = inflater.inflate(R.layout.item_common_image,null);
        ImageView img1 = (ImageView) c1.findViewById(R.id.img_common);
        img1.setImageResource(R.drawable.wallpaper1);
        View c2 = inflater.inflate(R.layout.item_common_image,null);
        ImageView img2 = (ImageView) c2.findViewById(R.id.img_common);
        img2.setImageResource(R.drawable.wallpaper2);
        View c3 = inflater.inflate(R.layout.item_common_image,null);
        ImageView img3 = (ImageView) c3.findViewById(R.id.img_common);
        img3.setImageResource(R.drawable.wallpaper3);
        View c4 = inflater.inflate(R.layout.item_common_image,null);
        ImageView img4 = (ImageView) c4.findViewById(R.id.img_common);
        img1.setImageResource(R.drawable.wallpaper4);
        viewflipper.addView(c1);
        viewflipper.addView(c2);
        viewflipper.addView(c3);
        viewflipper.addView(c4);
        viewflipper.startFlipping();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
           /* throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
