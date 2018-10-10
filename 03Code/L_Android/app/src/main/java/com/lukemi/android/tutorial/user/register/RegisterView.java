package com.lukemi.android.tutorial.user.register;


public interface RegisterView {

    void loading();

    void hideLoading();

    void registerResult(boolean result);
}

