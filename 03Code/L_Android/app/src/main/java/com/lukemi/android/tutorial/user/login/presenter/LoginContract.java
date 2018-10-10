package com.lukemi.android.tutorial.user.login.presenter;

import com.lukemi.android.tutorial.base.BasePresenter;
import com.lukemi.android.tutorial.base.BaseView;
import com.lukemi.android.tutorial.user.login.bean.LoginBeanRB;

public class LoginContract {

    public interface View extends BaseView<Presenter> {
        void setLoginResult(boolean loginSuccess);
    }

    public interface Presenter extends BasePresenter {
        void login(LoginBeanRB loginBean);
    }

}
