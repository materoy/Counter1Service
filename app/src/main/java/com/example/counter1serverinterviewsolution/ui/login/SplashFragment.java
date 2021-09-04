package com.example.counter1serverinterviewsolution.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.counter1serverinterviewsolution.R;
import com.example.counter1serverinterviewsolution.ui.notes.NotesListViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private static final String TAG = "SPLASH_FRAGMENT";

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel.getAuthenticationState().observe(this.getViewLifecycleOwner(), authenticationStateObserver);

    }

    Observer authenticationStateObserver = new Observer<AuthenticationState>() {
        @Override
        public void onChanged(AuthenticationState authenticationState) {
            switch (authenticationState){
                case Authenticated:
                    NavHostFragment.findNavController(SplashFragment.this).navigate(R.id.action_splashFragment_to_notesListFragment);
                    break;
                case Unauthenticated:
                    NavHostFragment.findNavController(SplashFragment.this).navigate(R.id.action_splashFragment_to_loginFragment);
                    break;
            }
        }
    };

}