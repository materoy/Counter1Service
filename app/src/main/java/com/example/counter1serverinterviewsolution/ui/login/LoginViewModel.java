package com.example.counter1serverinterviewsolution.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;

import android.os.AsyncTask;
import android.util.Patterns;

import com.example.counter1serverinterviewsolution.R;
import com.example.counter1serverinterviewsolution.data.model.LoggedInUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

enum AuthenticationState { Authenticated , Unauthenticated }

public class LoginViewModel extends ViewModel {
    private static LoginViewModel instance;

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<AuthenticationState> authenticationStateMutableLiveData;
    private FirebaseAuth mAuth ;

    LoginViewModel() {
        mAuth = FirebaseAuth.getInstance();
        authenticationStateMutableLiveData = new MutableLiveData<>();
        initAuthenticationState();
    }

    public static LoginViewModel getInstance() {
        if (instance == null) {
            instance = new LoginViewModel();
        }
        return instance;
    }

    LiveData<AuthenticationState> getAuthenticationState() {
        return authenticationStateMutableLiveData;
    }

    public LoggedInUser getUser(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        return new LoggedInUser(firebaseUser.getUid(), firebaseUser.getDisplayName());
    }

    private void initAuthenticationState(){
        if (mAuth.getCurrentUser() != null) {
            authenticationStateMutableLiveData.setValue(AuthenticationState.Authenticated);
        } else {
            authenticationStateMutableLiveData.setValue(AuthenticationState.Unauthenticated);
        }
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }


    public void login(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(task.getResult().getUser() != null){
                        authenticationStateMutableLiveData.setValue(AuthenticationState.Authenticated);
                    }
                }
            }
        });

    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }


}