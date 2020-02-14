package com.example.week2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.week2.R;
import com.example.week2.adapters.NoteItemAdapter;
import com.example.week2.helpers.EditFragmentHelper;
import com.example.week2.helpers.MainFragmentHelper;
import com.example.week2.items.NoteItem;
import com.example.week2.control.Router;

import java.util.Objects;

public class MainFragment extends Fragment {

    private MainFragmentHelper helper;

    private NoteItem note;
    private boolean editMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initHelper();
        TextView emptyDataBase = view.findViewById(R.id.mainFragmentNoNotesTextView);
        if (helper.getNotes().size() != 0) {
            initRecycleView();
            emptyDataBase.setVisibility(View.INVISIBLE);
        } else {

            emptyDataBase.setVisibility(View.VISIBLE);
        }
        initAddButton();
    }

    private void initHelper() {
        helper = new MainFragmentHelper(this.getContext());
    }

    private void initRecycleView() {
        RecyclerView notesRecycleView = Objects.requireNonNull(getView()).findViewById(R.id.mainFragmentNotesRecycleView);
        NoteItemAdapter adapter = new NoteItemAdapter();
        adapter.setNotes(helper.getSortedNotes());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        notesRecycleView.setAdapter(adapter);
        notesRecycleView.setLayoutManager(layoutManager);
    }

    private void initAddButton() {
        Button addButton = Objects.requireNonNull(getView()).findViewById(R.id.mainFragmentAddNewNoteButton);
        addButton.setOnClickListener(v -> {
            Router router = new Router(getContext());
            EditFragmentHelper editHelper = new EditFragmentHelper();
            NoteItem note = new NoteItem();
            note.setColor("WHITE");
            note.setSignificance(false);
            editHelper.setNote(note);
            router.addEditFragment(editHelper);
        });
    }


}
