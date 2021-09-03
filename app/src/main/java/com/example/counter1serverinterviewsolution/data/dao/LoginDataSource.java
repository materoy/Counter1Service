package com.example.counter1serverinterviewsolution.data.dao;

import android.util.Log;

import com.example.counter1serverinterviewsolution.data.Result;
import com.example.counter1serverinterviewsolution.data.model.LoggedInUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    FirebaseAuth mAuth;

    public Result<LoggedInUser> login(String email, String password) {

        try {
            final AuthResult authResult = (AuthResult) mAuth.signInWithEmailAndPassword(email, password);
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                FirebaseUser user = mAuth.getCurrentUser();
//                            } else {
//                                // If sign in fails, display a message to the user.
//
//
//                            }
//                        }
//                    });
            final FirebaseUser firebaseUser = authResult.getUser();
            Log.d("Login", "signInWithEmail:success");

            return new Result.Success<>(new LoggedInUser(firebaseUser.getUid(), firebaseUser.getDisplayName()));
        } catch (Exception e) {
            Log.w("Login", "signInWithEmail:failure", e.getCause());
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}