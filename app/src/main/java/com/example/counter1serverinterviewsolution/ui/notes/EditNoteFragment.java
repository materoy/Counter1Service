package com.example.counter1serverinterviewsolution.ui.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.counter1serverinterviewsolution.R;
import com.example.counter1serverinterviewsolution.data.model.Note;
import com.example.counter1serverinterviewsolution.databinding.FragmentEditNoteBinding;
import com.example.counter1serverinterviewsolution.ui.login.LoginViewModel;
import com.example.counter1serverinterviewsolution.ui.login.LoginViewModelFactory;

import java.util.Calendar;

public class EditNoteFragment extends Fragment {

    private NotesListViewModel mViewModel;
    private LoginViewModel loginViewModel;

    private FragmentEditNoteBinding binding;

    private static final String ARG_NOTE = "note";

    private Note note;

    public EditNoteFragment() {
        // Required empty public constructor
    }

    public static EditNoteFragment newInstance(String param1, String param2) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this, new NotesListViewModelFactory()).get(NotesListViewModel.class);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditNoteBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText noteTitle = binding.editTextTitle;
        EditText noteBody = binding.editTextNoteBody;

        Button addNoteButton = binding.buttonAddNote;

        /// Navigates to the add note page
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = noteTitle.getText().toString();
                String body = noteBody.getText().toString();
                if(!title.isEmpty() && !body.isEmpty()){
                    note = new Note(title, body, Calendar.getInstance().getTime());

                    /// Adds a note to the view model
                    mViewModel.createNote(note);
                    NavHostFragment.findNavController(EditNoteFragment.this).navigate(R.id.action_editNoteFragment_to_notesListFragment);
                }
            }
        });
    }
}