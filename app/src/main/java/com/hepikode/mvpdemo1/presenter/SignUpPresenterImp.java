package com.hepikode.mvpdemo1.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hepikode.mvpdemo1.view.SignUpView;

/**
 * Created by DhytoDev on 3/5/17.
 */

public class SignUpPresenterImp implements SignUpPresenter {

    private SignUpView signUpView ;
    private FirebaseAuth auth ;

    public SignUpPresenterImp(FirebaseAuth auth, SignUpView signUpView) {
        this.signUpView = signUpView;
        this.auth = auth ;
    }

    @Override
    public void signUp(String email, String password) {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ) {
            signUpView.showValidationError();
        } else {

            signUpView.setProgressVisibility(true);
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) signUpView, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            signUpView.setProgressVisibility(false);

                            if (!task.isSuccessful()) {
                                signUpView.signUpError();
                            } else {
                                signUpView.signUpSuccess();
                            }
                        }
                    });
        }
    }
}
