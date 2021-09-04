
package com.example.counter1serverinterviewsolution.ui.notes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.counter1serverinterviewsolution.R;
import com.example.counter1serverinterviewsolution.data.model.Note;
import com.example.counter1serverinterviewsolution.databinding.FragmentNotesListBinding;
import com.example.counter1serverinterviewsolution.ui.login.LoginViewModel;
import com.example.counter1serverinterviewsolution.ui.login.LoginViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NotesListFragment extends Fragment {
    private static final String TAG = "NotesListFragment";

    private FragmentNotesListBinding binding;

    private NotesListViewModel mViewModel;
    private LoginViewModel loginViewModel;
    private RecyclerView notesRecyclerView;
    private NotesRecyclerViewAdapter notesRecyclerViewAdapter;

    public static NotesListFragment newInstance() {
        return new NotesListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new NotesListViewModelFactory()).get(NotesListViewModel.class);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesListBinding.inflate(inflater, container, false);

        notesRecyclerView = binding.recyclerViewNotes;

        mViewModel.getLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if(loading){
                    binding.loading.setVisibility(View.VISIBLE);
                } else {
                    binding.loading.setVisibility(View.GONE);
                }
            }
        });

        mViewModel.getNotesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Note>>() {
            @Override
            public void onChanged(ArrayList<Note> notes) {
                notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(notes);
                notesRecyclerView.setLayoutManager(new LinearLayoutManager(NotesListFragment.this.getContext()));
                notesRecyclerView.setAdapter(notesRecyclerViewAdapter);

                notesRecyclerViewAdapter.setOnItemClickListener(new NotesRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("NotePosition", position);
                        NavHostFragment.findNavController(NotesListFragment.this).navigate(R.id.action_notesListFragment_to_viewNoteFragment, bundle);
                    }
                });
            }
        });


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /// Initializes the userParam in the Notes view model
        mViewModel.init(loginViewModel.getUser());

        FloatingActionButton fab = binding.fabAddNote;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NotesListFragment.this)
                        .navigate(R.id.action_notesListFragment_to_editNoteFragment);
            }
        });
    }
}