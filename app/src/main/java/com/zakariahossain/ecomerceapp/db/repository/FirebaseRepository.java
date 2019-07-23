package com.zakariahossain.ecomerceapp.db.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.zakariahossain.ecomerceapp.ui.auth.AuthListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseRepository {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public LiveData<Map<String, String>> forgotPassword(String email) {
        MutableLiveData<Map<String, String>> forgotResponse = new MutableLiveData<>();
        Map<String, String> forgotMap = new HashMap<>();

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    forgotMap.put("recovery_success", "Recovery email sent successfully. Please check your inbox");
                    forgotResponse.setValue(forgotMap);
                } else {
                    forgotMap.put("recovery_failed", Objects.requireNonNull(task.getException()).getMessage());
                    forgotMap.put("recovery_error", "failed recovery");
                    forgotResponse.setValue(forgotMap);
                }
            }
        });

        return forgotResponse;
    }

    public LiveData<Map<String, String>> loginWithEmailPassword(String email, String password) {
        MutableLiveData<Map<String, String>> loginResponse = new MutableLiveData<>();
        Map<String, String> loginMap = new HashMap<>();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loginMap.put("login_success", Objects.requireNonNull(task.getResult()).getUser().getUid());
                    loginResponse.setValue(loginMap);
                } else {
                    loginMap.put("login_failed", Objects.requireNonNull(task.getException()).getMessage());
                    loginMap.put("login_error", "login failed");
                    loginResponse.setValue(loginMap);
                }
            }
        });

        return loginResponse;
    }

    public LiveData<Map<String, String>> signUpWithEmailPassword(String name, String email, String password) {
        MutableLiveData<Map<String, String>> signUpResponse = new MutableLiveData<>();
        Map<String, String> signUpMap = new HashMap<>();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<Object, String> userData = new HashMap<>();
                    userData.put("name", name);

                    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                    firebaseFirestore.collection("Users").add(userData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                signUpMap.put("signup_success", Objects.requireNonNull(task.getResult()).getId());
                                signUpResponse.setValue(signUpMap);
                            } else {
                                signUpMap.put("signup_failed", Objects.requireNonNull(task.getException()).getMessage());
                                signUpMap.put("signup_error", "signup failed");
                                signUpResponse.setValue(signUpMap);
                            }
                        }
                    });
                } else {
                    signUpMap.put("signup_failed", Objects.requireNonNull(task.getException()).getMessage());
                    signUpMap.put("signup_error", "signup failed");
                    signUpResponse.setValue(signUpMap);
                }
            }
        });

        return signUpResponse;
    }
}
