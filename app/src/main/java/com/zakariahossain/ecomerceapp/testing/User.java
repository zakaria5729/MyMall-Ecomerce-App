package com.zakariahossain.ecomerceapp.testing;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class User {
    private String email, password;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public boolean isValidData() {
        return !TextUtils.isEmpty(getEmail()) && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() && getEmail().length() > 8;
    }
}
