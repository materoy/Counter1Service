package com.example.counter1serverinterviewsolution.ui.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.counter1serverinterviewsolution.data.SampleNotes;
import com.example.counter1serverinterviewsolution.data.model.Note;
import com.example.counter1serverinterviewsolution.data.repository.NotesRepository;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotesListViewModel extends ViewModel {
    private NotesRepository notesRepository;
    private MutableLiveData<ArrayList<Note>> notesLiveData;
    private ArrayList<Note> notesArrayList;

    NotesListViewModel(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
        notesLiveData = new MutableLiveData<>();
        init();
        System.out.println("Constructor called");
    }
    public MutableLiveData<ArrayList<Note>> getNotesMutableLiveData() { return notesLiveData; }

    public  void init(){
        populateList();
        notesLiveData.setValue(notesArrayList);
    }

    public void populateList(){
        notesArrayList = new ArrayList<>();
        notesArrayList.addAll(Arrays.asList(SampleNotes.sampleNotes));
    }

    public void createNote(Note note) {
//        notesRepository.createNote(note);
        notesArrayList.add(note);
        notesLiveData.setValue(notesArrayList);
    }

    public void getNote(String noteId) {
    }

    public void getAllNotes() {
    }

    public void editNote(Note note) {
    }

    public void deleteNote(String noteId) {
    }
}