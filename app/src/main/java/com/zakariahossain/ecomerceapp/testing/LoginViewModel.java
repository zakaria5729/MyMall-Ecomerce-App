package com.zakariahossain.ecomerceapp.testing;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private User user;
    private LoginCallback loginCallback;

    public LoginViewModel(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
        this.user = new User();
    }

    public void onLoginClick() {

    }
}
