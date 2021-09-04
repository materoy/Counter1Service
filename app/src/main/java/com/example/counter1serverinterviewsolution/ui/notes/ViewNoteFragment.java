package com.example.counter1serverinterviewsolution.ui.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.counter1serverinterviewsolution.R;
import com.example.counter1serverinterviewsolution.data.model.Note;
import com.example.counter1serverinterviewsolution.databinding.FragmentViewNoteBinding;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewNoteFragment extends Fragment {
    private static final String TAG = "ViewNoteFragment";
    private static final String ARG_PARAM1 = "NotePosition";
    private NotesListViewModel mViewModel;
    private Note mNote;

    private FragmentViewNoteBinding binding;

    private int position;

    public ViewNoteFragment() {
        // Required empty public constructor
    }

    public static ViewNoteFragment newInstance(String param1, String param2) {
        ViewNoteFragment fragment = new ViewNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_PARAM1);
        }

        mViewModel = new ViewModelProvider(this, new NotesListViewModelFactory()).get(NotesListViewModel.class);
        mNote = mViewModel.getNotesMutableLiveData().getValue().get(position);

        Log.e(TAG, "onCreate: " + mNote.getTitle() );

        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentViewNoteBinding.inflate(inflater, container, false);

        EditText titleEditText = binding.editTextNoteTitle;
        EditText bodyEditText = binding.editTextNoteBody;

        titleEditText.setText(mNote.getTitle());
        bodyEditText.setText(mNote.getBody());

        Button saveButton = binding.buttonSave;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String body = bodyEditText.getText().toString();

                mViewModel.editNote(new Note(title, body, Calendar.getInstance().getTime(), mNote.getNoteId()), mNote);
            }
        });

        binding.buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.deleteNote(mNote);
                NavHostFragment.findNavController(ViewNoteFragment.this).navigate(R.id.action_viewNoteFragment_to_notesListFragment);
            }
        });
        return binding.getRoot();
    }
}