package com.example.counter1serverinterviewsolution.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public final class Note {
    private  String noteId;
    private  String title;
    private  String body;
    private  Date timestamp;

    public Note(String title, String body, Date timestamp) {
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.noteId = UUID.randomUUID().toString();
    }

    public Note(String title, String body, Date timestamp, String noteId) {
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.noteId = noteId;
    }

    public Note(){}

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

