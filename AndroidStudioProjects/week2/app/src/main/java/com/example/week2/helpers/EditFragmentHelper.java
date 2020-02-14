package com.example.week2.helpers;

import com.example.week2.items.NoteItem;

public class EditFragmentHelper {

    public NoteItem getNote() {
        return note;
    }

    public void setNote(NoteItem note) {
        this.note = note;
    }

    public void setMode(boolean mode) {
        this.editMode = mode;
    }

    public boolean isEditMode() {
        return editMode;
    }
}
