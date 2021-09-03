package com.example.counter1serverinterviewsolution.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Note {
    private final String noteId;
    private final String title;
    private final String body;
    private final Date timestamp;

    public Note(String title, String body, Date timestamp, String noteId) {
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getNoteId() {
        return noteId;
    }
}

