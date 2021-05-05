package com.lukemi.android.tutorial.system;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lukemi.android.common.IntentJumpAdapter;
import com.lukemi.android.common.IntentJumpBean;
import com.lukemi.android.tutorial.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author mzchen
 * @date 2019/6/18 20:36
 * @des android 调用系统分享
 * @link https://www.cnblogs.com/huolongluo/p/7774870.html
 * @mail chenmingzhiji@163.com
 */
public class SystemShareActivity extends AppCompatActivity {

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;
    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull android.view.View view, int position) {

            IntentJumpBean bean = (IntentJumpBean) adapter.getData().get(position);
            switch (bean.getFlag()) {
                case 1:
                    textShare();
                    break;
                case 2:
                    picShare();
                    break;
                case 3:
                    picsShare();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_share);
        ButterKnife.bind(this);
        initAdapter();
        initView();
    }

    private void initAdapter() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("文本 - 分享", null, 1));
        intentJumpBeanList.add(new IntentJumpBean("单张图片 - 分享", null, 2));
        intentJumpBeanList.add(new IntentJumpBean("多张图片 - 分享", null, 3));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }

    /**
     * 文本分享
     */
    private void textShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "文本分享,这个是文本内容");
        startActivity(Intent.createChooser(intent, "这是内容，文本分享"));
    }

    /**
     * 单张图片上传
     */
    private void picShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        String uri = Environment.getRootDirectory() + File.separator + "DCIM" + File.separator + "qrcode.jpg";
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "百度图片"));
    }

    /**
     * 多张图片上传
     */
    private void picsShare() {
        ArrayList<CharSequence> imageUris = new ArrayList<>();
        imageUris.add("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=2399746921,3003152069&fm=85&s=695229C7078F2949395C049C0300D0C3");
        imageUris.add("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=1664561473,1230442763&fm=85&s=08808A5D9AA3C64754906C9403008062");
        Intent mulIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        mulIntent.putCharSequenceArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        mulIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(mulIntent, "多图文件分享"));
    }
}
