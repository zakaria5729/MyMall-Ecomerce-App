package com.zakariahossain.ecomerceapp.ui.auth;

import androidx.lifecycle.LiveData;

import java.util.Map;

public interface AuthListener {
    void onStarted();
    void onSuccess(LiveData<Map<String, String>> response);
    void onFailure(String errorMsg);
}
