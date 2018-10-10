package com.lukemi.android.tutorial.user.login.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.user.login.bean.LoginBeanRB;
import com.lukemi.android.tutorial.user.login.presenter.LoginContract;
import com.lukemi.android.tutorial.user.login.presenter.LoginPresenter;
import com.lukemi.android.tutorial.user.userInfo.bean.UserInfoBean;
import com.lukemi.android.tutorial.user.userInfo.ui.UserInfoActivity;
import com.lukemi.android.tutorial.util.Logcat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {


    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private String account;
    private String password;
    private LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
        initView();
        Logcat.log("---------LoginActivity--------onCreate---------");
    }

    @Override
    protected void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    private void initData() {
        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        InitialValueObservable<CharSequence> etAccountObservable = RxTextView.textChanges(etAccount);
        InitialValueObservable<CharSequence> etPasswordObservable = RxTextView.textChanges(etPassword);
        disposable = Observable.combineLatest(etAccountObservable, etPasswordObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            /**
             *
             * @param accountStr 帐号
             * @param passwordStr 密码
             * @return 等录按钮是否可用
             * @throws Exception 相关异常
             */
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

            /**
             *
             * @param btnLoginIsEnable 登录是否可用
             * @throws Exception 相关异常
             */
            @Override
            public void accept(Boolean btnLoginIsEnable) {
                btnLogin.setEnabled(btnLoginIsEnable);
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        LoginBeanRB loginBeanRB = new LoginBeanRB(account, password);
        loginPresenter.login(loginBeanRB);
    }

    @Override
    public void setLoginResult(boolean loginSuccess) {
        closeLoginLoading();
        Toast.makeText(this, loginSuccess ? "Loading Success" : "Loading Failed", Toast.LENGTH_SHORT).show();
        if (loginSuccess) {
            Intent intent = new Intent(this, UserInfoActivity.class);
            intent.putExtra("userBean", new UserInfoBean("王五", 0, 20, "12345678911", "教授"));
            startActivity(intent);
        }
    }

    @Override
    public void setProgress(LoginContract.Presenter presenter) {
        openLoginLoading();
    }

    /**
     * 打开 Loading
     */
    private void openLoginLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Login...");
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
