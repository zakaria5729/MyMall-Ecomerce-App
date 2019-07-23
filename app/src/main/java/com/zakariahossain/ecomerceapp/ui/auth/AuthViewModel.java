package com.zakariahossain.ecomerceapp.ui.auth;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zakariahossain.ecomerceapp.db.repository.FirebaseRepository;

import java.util.Map;

public class AuthViewModel extends ViewModel {

    private String email, password;
    private String name, confirmPassword;
    private String recoveryEmail;
    private AuthListener authListener;

    public void onResetPasswordButtonClick(View view) {
        authListener.onStarted();

        if (TextUtils.isEmpty(recoveryEmail)) {
            authListener.onFailure("Invalid email");
            return;
        }

        LiveData<Map<String, String>> forgotResponse = new FirebaseRepository().forgotPassword(recoveryEmail);
        authListener.onSuccess(forgotResponse);
    }

    public void onLoginButtonClicked(View view) {
        authListener.onStarted();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            authListener.onFailure("Invalid email or password");
            return;
        }

        LiveData<Map<String, String>> loginResponse = new FirebaseRepository().loginWithEmailPassword(email, password);
        authListener.onSuccess(loginResponse);
    }

    public void onSignUpButtonClicked(View view) {
        authListener.onStarted();

        if (TextUtils.isEmpty(name)) {
            authListener.onFailure("Name can't be empty!");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            authListener.onFailure("Email can't be empty!");
            return;
        }

        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            authListener.onFailure("Enter a valid email!");
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 8) {
            authListener.onFailure("Password should at least 8 characters!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            authListener.onFailure("Password did not matched!");
            return;
        }

        LiveData<Map<String, String>> signUpResponse = new FirebaseRepository().signUpWithEmailPassword(name, email, password);
        authListener.onSuccess(signUpResponse);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail = recoveryEmail;
    }

    public AuthListener getAuthListener() {
        return authListener;
    }

    public void setAuthListener(AuthListener authListener) {
        this.authListener = authListener;
    }

}
