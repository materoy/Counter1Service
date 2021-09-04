package com.example.counter1serverinterviewsolution.ui.notes;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.counter1serverinterviewsolution.R;
import com.example.counter1serverinterviewsolution.data.model.LoggedInUser;
import com.example.counter1serverinterviewsolution.data.model.Note;
import com.example.counter1serverinterviewsolution.ui.login.LoginViewModel;
import com.example.counter1serverinterviewsolution.ui.login.LoginViewModelFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NotesListViewModel extends ViewModel {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notesCollection;

    private LoggedInUser user;
    static final String USERS_COLLECTION = "users";
    static final String NOTES_COLLECTION = "notes";
    private static final String TAG = "NOTE LIST VIEW MODEL";
    private MutableLiveData<ArrayList<Note>> notesLiveData;
    private ArrayList<Note> notesArrayList;
    private MutableLiveData<Boolean> loading;

    NotesListViewModel() {
        notesLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Note>> getNotesLiveData() {
        return notesLiveData;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void init(LoggedInUser user) {
        this.user = user;
        notesCollection = db.collection(USERS_COLLECTION).document(user.getUserId()).collection(NOTES_COLLECTION);
        getAllNotes();
        notesLiveData.setValue(notesArrayList);
    }


    public void createNote(Note note) {
        notesCollection.document(note.getNoteId()).set(note);
        notesArrayList.add(note);
        notesLiveData.setValue(notesArrayList);
    }

    // Queries the database for all the notes for the current user
    public void getAllNotes() {
        notesArrayList = new ArrayList<>();
        loading.setValue(true);
        notesCollection.orderBy("timestamp").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Note note = document.toObject(Note.class);
                        notesArrayList.add(note);
                    }

                }
                loading.setValue(false);
                notesLiveData.setValue(notesArrayList);
            }
        });
    }

    public void editNote(Note newNote, Note oldNote) {
        int index = notesArrayList.indexOf(oldNote);
        notesArrayList.set(index, newNote);
        notesCollection.document(oldNote.getNoteId()).set(newNote);
    }

    public void deleteNote(Note note) {
        notesArrayList.remove(note);
        notesLiveData.setValue(notesArrayList);
        notesCollection.document(note.getNoteId()).delete();
    }
}