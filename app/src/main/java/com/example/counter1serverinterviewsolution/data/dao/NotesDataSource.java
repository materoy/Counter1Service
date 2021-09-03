package com.example.counter1serverinterviewsolution.data.dao;

import com.example.counter1serverinterviewsolution.data.model.Note;
import com.google.firebase.firestore.FirebaseFirestore;

public class NotesDataSource {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void createNote(Note note) {
        try {
            db.document(note.getNoteId()).set(note);
        } catch (Exception e){
            throw e;
        }
    }

    public void readNote(String noteId) {
    }

    public void readAllNotes() {
    }

    public void editNote(Note note) {
    }

    public void deleteNote(String noteId) {
    }

}
