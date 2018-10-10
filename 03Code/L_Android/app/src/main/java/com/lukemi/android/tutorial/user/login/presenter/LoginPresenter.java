package com.lukemi.android.tutorial.user.login.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lukemi.android.tutorial.user.login.bean.LoginBeanRB;
import com.lukemi.android.tutorial.user.login.ui.LoginActivity;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginActivity mContext;
    public LoginPresenter(@NonNull LoginActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    public void login(final LoginBeanRB loginBeanRB) {
        mContext.setProgress(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    String account = loginBeanRB.getAccount();
                    String password = loginBeanRB.getPassword();
                    final boolean loginSuccess  =  TextUtils.equals(account,"1") && TextUtils.equals(password,"1");
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mContext.setLoginResult(loginSuccess);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void start() {

    }
}
