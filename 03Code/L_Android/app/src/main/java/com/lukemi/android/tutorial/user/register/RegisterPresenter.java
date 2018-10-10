package com.lukemi.android.tutorial.user.register;

public class RegisterPresenter {

    private final RegisterView view;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
    }

    public void register(RegisterBean registerBean) {
        view.loading();

    }

}
