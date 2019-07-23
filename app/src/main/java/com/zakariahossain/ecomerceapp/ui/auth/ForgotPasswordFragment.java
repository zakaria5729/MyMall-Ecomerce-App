package com.zakariahossain.ecomerceapp.ui.auth;

import android.content.res.ColorStateList;
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
import com.zakariahossain.ecomerceapp.databinding.FragmentForgotPasswordBinding;
import com.zakariahossain.ecomerceapp.util.Helper;

import java.util.Map;

public class ForgotPasswordFragment extends Fragment implements AuthListener{

    private FragmentForgotPasswordBinding forgotPassBinding;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        forgotPassBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false);

        AuthViewModel forgotViewModel = ViewModelProviders.of(requireActivity()).get(AuthViewModel.class);
        forgotPassBinding.setForgotViewModel(forgotViewModel);
        forgotViewModel.setAuthListener(this);

        return forgotPassBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        forgotPassBinding.tvForgotGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.action_forgotPassword_to_login);
            }
        });
    }

    @Override
    public void onStarted() {
        forgotPassBinding.progressBarForgotPass.setVisibility(View.VISIBLE);
        forgotPassBinding.btnForgotPassword.setEnabled(false);
        forgotPassBinding.btnForgotPassword.setTextColor(Color.GRAY);
    }

    @Override
    public void onSuccess(LiveData<Map<String, String>> response) {
        response.observe(requireActivity(), new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> stringStringMap) {
                forgotPassBinding.progressBarForgotPass.setVisibility(View.GONE);

                if (stringStringMap.size() == 1) {
                    forgotPassBinding.etForgotPasswordEmail.setError(stringStringMap.get("recovery_success"));
                    forgotPassBinding.etForgotPasswordEmail.setBoxStrokeColor(Color.GREEN);
                    Helper.showToast(requireActivity(), stringStringMap.get("recovery_success"));
                } else {
                    forgotPassBinding.btnForgotPassword.setEnabled(true);
                    forgotPassBinding.btnForgotPassword.setTextColor(Color.WHITE);
                    forgotPassBinding.etForgotPasswordEmail.setError(stringStringMap.get("recovery_failed"));
                }
            }
        });
    }

    @Override
    public void onFailure(String errorMsg) {
        forgotPassBinding.btnForgotPassword.setEnabled(true);
        forgotPassBinding.btnForgotPassword.setTextColor(Color.WHITE);
        forgotPassBinding.progressBarForgotPass.setVisibility(View.GONE);
        forgotPassBinding.etForgotPasswordEmail.setError(errorMsg);
    }
}
