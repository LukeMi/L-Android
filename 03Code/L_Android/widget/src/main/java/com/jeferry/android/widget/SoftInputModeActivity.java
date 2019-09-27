package com.jeferry.android.widget;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jeferry.android.widget.bean.SoftInputModeBean;
import com.jeferry.android.widget.flowlayout.FlowLayout;
import com.jeferry.android.widget.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * 软键盘弹出方式  两类参数 ： 软键盘状态 、调整状态
 */
public class SoftInputModeActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView btn;

    private PopupWindow mPop;

    private List<SoftInputModeBean> mSoftInputModeBeanList = new ArrayList<>();
    private SoftInputModeAdapter mSoftInputModeAdapter;
    private CheckBox cb;
    private Set<Integer> mSelectPosSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softinput_mode);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this::onViewClicked);
        initData();
        initPop();
    }

    private void initData() {
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_MASK_STATE",
                WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_STATE_UNSPECIFIED",
                WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_STATE_UNCHANGED",
                WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_STATE_HIDDEN",
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_STATE_ALWAYS_HIDDEN",
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_STATE_VISIBLE",
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_STATE_ALWAYS_VISIBLE",
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_MASK_ADJUST",
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_ADJUST_RESIZE",
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_ADJUST_PAN",
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_ADJUST_NOTHING",
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING));
        mSoftInputModeBeanList.add(new SoftInputModeBean("SOFT_INPUT_IS_FORWARD_NAVIGATION",
                WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION));

        mSoftInputModeAdapter = new SoftInputModeAdapter(mSoftInputModeBeanList, R.layout.item_pop_soft_input_mode, this
                , getWindow().getAttributes().softInputMode);
    }

    private void initPop() {
        View popView = getLayoutInflater().inflate(R.layout.pop_softinput_mode, null);
        TextView tvConfirm = popView.findViewById(R.id.tv_confirm);
        TagFlowLayout tagFlowLayout = popView.findViewById(R.id.tagFlowLayout);
        cb = popView.findViewById(R.id.cb_control);
        tvConfirm.setOnClickListener((v) -> {
            Iterator<Integer> iterator = mSelectPosSet.iterator();
            int mode = 0;
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                mode |= mSoftInputModeBeanList.get(next).getMode();
            }
            getWindow().setSoftInputMode(mode);
            mPop.dismiss();
        });

        cb.setOnCheckedChangeListener((compoundButton, b) -> {
            tagFlowLayout.setMaxSelectCount(b ? 1 : TagFlowLayout.TAG_SELECT_NO_LIMIT);
            tvConfirm.setEnabled(!b);
        });


        tagFlowLayout.setAdapter(mSoftInputModeAdapter);
        tagFlowLayout.setOnTagClickListener((View view, int position, FlowLayout parent) -> {
            if (cb.isChecked()) {
                int mode = mSoftInputModeBeanList.get(position).getMode();
                getWindow().setSoftInputMode(mode);
                mPop.dismiss();
            }
            return false;
        });
        tagFlowLayout.setOnSelectListener((Set<Integer> selectPosSet) -> {
            this.mSelectPosSet = selectPosSet;
        });

        mPop = new PopupWindow(popView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPop.setBackgroundDrawable(new ColorDrawable());
        mPop.setFocusable(true);
        mPop.setOutsideTouchable(false);
    }


    public void onViewClicked(View view) {
        if (mPop != null) {
            mPop.showAtLocation(et1, Gravity.BOTTOM, -btn.getHeight(), 0);
        }
    }
}