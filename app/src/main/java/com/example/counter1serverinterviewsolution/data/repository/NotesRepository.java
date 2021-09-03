package com.example.counter1serverinterviewsolution.data.repository;


import com.example.counter1serverinterviewsolution.data.dao.NotesDataSource;
import com.example.counter1serverinterviewsolution.data.model.Note;

public class NotesRepository {

    private static volatile NotesRepository instance;

    private NotesDataSource dataSource;

    private NotesRepository(NotesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static NotesRepository getInstance(NotesDataSource dataSource) {
        if (instance == null) {
            instance = new NotesRepository(dataSource);
        }
        return instance;
    }

    public void createNote(Note note) {
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
