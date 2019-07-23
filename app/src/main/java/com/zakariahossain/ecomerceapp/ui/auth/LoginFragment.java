package com.zakariahossain.ecomerceapp.ui.auth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.FragmentLoginBinding;
import com.zakariahossain.ecomerceapp.ui.home.MainActivity;
import com.zakariahossain.ecomerceapp.util.Helper;

import java.util.Map;

public class LoginFragment extends Fragment implements AuthListener {

    private FragmentLoginBinding loginBinding;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        AuthViewModel authViewModel = ViewModelProviders.of(requireActivity()).get(AuthViewModel.class);
        loginBinding.setAuthViewModel(authViewModel);
        authViewModel.setAuthListener(this);

        return loginBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBinding.tvSignUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment).navigate(R.id.action_login_to_signUp);
            }
        });

        loginBinding.ivCloseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToMainActivity();
            }
        });

        loginBinding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.action_login_to_forgotPassword);
            }
        });
    }

    @Override
    public void onStarted() {
        loginBinding.progressBarLogin.setVisibility(View.VISIBLE);
        loginBinding.btnLogin.setEnabled(false);
        loginBinding.btnLogin.setTextColor(Color.GRAY);
    }

    @Override
    public void onSuccess(LiveData<Map<String, String>> response) {
        response.observe(requireActivity(), new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> stringStringMap) {
                loginBinding.btnLogin.setEnabled(true);
                loginBinding.btnLogin.setTextColor(Color.WHITE);
                loginBinding.progressBarLogin.setVisibility(View.GONE);

                if (stringStringMap.size() == 1) {
                    sendToMainActivity();
                } else {
                    Helper.showToast(requireActivity(), stringStringMap.get("login_failed"));
                }
            }
        });
    }

    @Override
    public void onFailure(String errorMsg) {
        loginBinding.btnLogin.setEnabled(true);
        loginBinding.btnLogin.setTextColor(Color.WHITE);
        loginBinding.progressBarLogin.setVisibility(View.GONE);
        Helper.showToast(requireContext(), errorMsg);
    }

    private void sendToMainActivity() {
        startActivity(new Intent(requireActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        requireActivity().finish();
    }
}
