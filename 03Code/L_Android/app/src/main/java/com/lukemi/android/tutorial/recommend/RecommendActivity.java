package com.lukemi.android.tutorial.recommend;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;
import com.lukemi.android.tutorial.recommend.presenter.RecommendPresenter;
import com.lukemi.android.tutorial.recommend.view.RecommendView;

import butterknife.BindView;

public class RecommendActivity extends AbsBaseActivity
        implements RecommendView<String> {

    @BindView(R.id.rv_tt)
    RecyclerView rvTt;
    @BindView(R.id.img_bkg)
    ImageView imgBkg;
    private RecommendPresenter recommendPresenter;
    String img = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=449224247,2721763316&fm=27&gp=0.jpg";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refresh();
        Logcat.log("---------onCreate---------");
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void bindPresenter() {
        recommendPresenter = new RecommendPresenter(this);
    }

    @Override
    protected void onDestroy() {
//        recommendPresenter.onDestroy();
        Logcat.log("-----------onDestroy--------");
        super.onDestroy();
    }

    private void refresh() {
        Logcat.log("refresh");
        recommendPresenter.refresh();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @Override
    public void loading() {
//        openLoginLoading();
    }

    @Override
    public void hindLoading() {
        closeLoginLoading();
    }


    @Override
    public void onSuccess(String s) {
        Logcat.log("))))))))))))))))))))))))" + s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }


    /**
     * 打开 Loading
     */
    private void openLoginLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Register...");
        progressDialog.show();
    }

    /**
     * 关闭 Loading
     */
    private void closeLoginLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
