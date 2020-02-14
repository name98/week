package com.example.week2.helpers;

import android.content.Context;

import com.example.week2.database.DataBaseHelper;
import com.example.week2.items.NoteItem;

import java.util.ArrayList;

public class MainFragmentHelper {
    private Context context;

    public MainFragmentHelper(Context context) {
        this.context = context;
    }

    public ArrayList<NoteItem> getNotes(){
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getNotes();
    }
    public ArrayList<NoteItem> getSortedNotes(){
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getSortedNotes();
    }


}
