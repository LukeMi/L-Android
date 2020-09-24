package com.jeferry.android.widget.bottomsheet.list;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.jeferry.android.widget.R;
import com.jeferry.android.widget.bottomsheet.BaseBottomSheetDialogFragment;
import com.socks.library.KLog;

import java.util.ArrayList;

public class ListBottomSheetDialogFragment extends BaseBottomSheetDialogFragment {

    private final String TAG = ListBottomSheetDialogFragment.class.getSimpleName();

    private float def = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, Resources.getSystem().getDisplayMetrics());

    private TextView mTvTitle;

    private ConstraintLayout mClInfo;

    private RecyclerView mRv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_list_bottom_sheet_dialog, container, false);
    }

    private int overallYScroll = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fixHeight = 1200;
        mClInfo = view.findViewById(R.id.cl_view_head_bottom_sheet);
        mTvTitle = view.findViewById(R.id.tv_title);
        mRv = view.findViewById(R.id.rv);

        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                overallYScroll += dy;
                KLog.d(TAG, "dx : " + dx + " ;dy : " + dy
                        + " ;height: " + recyclerView.getHeight()
                        + " ;measureHeight : " + recyclerView.getMeasuredHeight()
                        + " ;y : " + recyclerView.getY()
                        + " ;top : " + recyclerView.getTop()
                        + " ;overallYScroll : " + overallYScroll
                );
                mClInfo.setVisibility(def < overallYScroll ? View.VISIBLE : View.GONE);
                mTvTitle.setVisibility(def < overallYScroll ? View.GONE : View.VISIBLE);
            }
        });
        mRv.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    // Disallow NestedScrollView to intercept touch events.
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    break;

                case MotionEvent.ACTION_UP:
                    // Allow NestedScrollView to intercept touch events.
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            KLog.d(TAG, v.getClass().getName());
            v.onTouchEvent(event);
            return true;
        });
        mock();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void mock() {
        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Person person = new Person();
            person.name = "name : " + i;
            person.info = "info : " + i;
            people.add(person);
        }
        ListBottomAdapter listBottomAdapter = new ListBottomAdapter();
        listBottomAdapter.setOnChildClickListener((view, position) -> {
            Toast.makeText(getContext().getApplicationContext(), "position : " + position, Toast.LENGTH_SHORT).show();
        });
        mRv.setAdapter(listBottomAdapter);
        listBottomAdapter.replaceData(people);
    }


}
