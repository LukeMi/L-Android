package com.lukemi.android.tutorial.user.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;
import com.lukemi.android.common.util.Logcat;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * 注册界面
 */
public class RegisterActivity extends AbsBaseActivity implements RegisterView {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private Disposable subscribe;
    private String account;
    private String password;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logcat.log("------RegisterActivity-------onCreate------");
    }


    protected void initView() {
       /* InitialValueObservable<CharSequence> etAccountObservable = RxTextView.textChanges(etAccount);
        InitialValueObservable<CharSequence> etPasswordObservable = RxTextView.textChanges(etPassword);
        subscribe = Observable.combineLatest(etAccountObservable, etPasswordObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            *//**
             *
             * @param accountStr 帐号
             * @param passwordStr 密码
             * @return 等录按钮是否可用
             * @throws Exception 相关异常
             *//*
            @Override
            public Boolean apply(CharSequence accountStr, CharSequence passwordStr) {
                account = accountStr.toString();
                password = passwordStr.toString();
                Log.i("account = ", account);
                Log.i("password = ", password);
                boolean accountIsNotEmpty = !TextUtils.isEmpty(accountStr);
                boolean passwordIsNotEmpty = !TextUtils.isEmpty(passwordStr);
                return accountIsNotEmpty && passwordIsNotEmpty;
            }
        }).subscribe(new Consumer<Boolean>() {

            *//**
             *
             * @param btnLoginIsEnable 登录是否可用
             * @throws Exception 相关异常
             *//*
            @Override
            public void accept(Boolean btnLoginIsEnable) {
                btnLogin.setEnabled(btnLoginIsEnable);
            }
        });*/
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                register();
                break;
        }
    }

    private void register() {
        RegisterBean registerBean = new RegisterBean(account, password);
    }


    @Override
    protected int bindLayout() {
        return R.layout.activity_register;
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

    @Override
    public void loading() {
        openLoginLoading();
    }

    @Override
    public void hideLoading() {
        closeLoginLoading();
    }

    @Override
    public void registerResult(boolean result) {

    }
}
