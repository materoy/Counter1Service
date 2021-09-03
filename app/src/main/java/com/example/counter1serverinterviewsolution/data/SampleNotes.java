package com.example.counter1serverinterviewsolution.data;

import com.example.counter1serverinterviewsolution.data.model.Note;

import java.util.Calendar;
import java.util.Date;

public class SampleNotes {
    static Date currentTime = Calendar.getInstance().getTime();
    static public final Note[] sampleNotes = {new Note("Title1", "body1", currentTime, ""),
            new Note("Title 2", "", currentTime, ""),
            new Note("Title 3", "", currentTime, "")};
}
