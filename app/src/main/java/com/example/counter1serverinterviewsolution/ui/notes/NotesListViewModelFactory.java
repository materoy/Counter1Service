package com.example.counter1serverinterviewsolution.ui.notes;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.counter1serverinterviewsolution.data.dao.NotesDataSource;
import com.example.counter1serverinterviewsolution.data.repository.NotesRepository;


public class NotesListViewModelFactory implements  ViewModelProvider.Factory {

    private static volatile NotesListViewModel instance;

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NotesListViewModel.class)) {
            return (T) NotesListViewModelFactory.getInstance(NotesRepository.getInstance(new NotesDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

    public static NotesListViewModel getInstance(NotesRepository notesRepository) {
        if (instance == null) {
            instance = new NotesListViewModel(notesRepository);
        }
        return instance;
    }
}


