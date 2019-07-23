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
import com.zakariahossain.ecomerceapp.databinding.FragmentSignUpBinding;
import com.zakariahossain.ecomerceapp.ui.home.MainActivity;
import com.zakariahossain.ecomerceapp.util.Helper;

import java.util.Map;

public class SignUpFragment extends Fragment implements AuthListener {

    private FragmentSignUpBinding signUpBinding;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        signUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);

        AuthViewModel authViewModel = ViewModelProviders.of(requireActivity()).get(AuthViewModel.class);
        signUpBinding.setAuthViewModel(authViewModel);
        authViewModel.setAuthListener(this);

        return signUpBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        signUpBinding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signUp_to_login);
            }
        });

        signUpBinding.ivCloseSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToMainActivity();
            }
        });
    }

    @Override
    public void onStarted() {
        signUpBinding.progressBarSignUp.setVisibility(View.VISIBLE);
        signUpBinding.btnSignUp.setEnabled(false);
        signUpBinding.btnSignUp.setTextColor(Color.GRAY);
    }

    @Override
    public void onSuccess(LiveData<Map<String, String>> response) {
        response.observe(requireActivity(), new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> stringStringMap) {
                signUpBinding.btnSignUp.setEnabled(true);
                signUpBinding.btnSignUp.setTextColor(Color.WHITE);
                signUpBinding.progressBarSignUp.setVisibility(View.GONE);

                if (stringStringMap.size() == 1) {
                    sendToMainActivity();
                } else {
                    Helper.showToast(requireActivity(), stringStringMap.get("signup_failed"));
                }
            }
        });
    }

    @Override
    public void onFailure(String errorMsg) {
        signUpBinding.btnSignUp.setEnabled(true);
        signUpBinding.btnSignUp.setTextColor(Color.WHITE);
        signUpBinding.progressBarSignUp.setVisibility(View.GONE);
        Helper.showToast(requireContext(), errorMsg);
    }

    private void sendToMainActivity() {
        startActivity(new Intent(requireActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        requireActivity().finish();
    }
}
