package com.example.week2.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.R;
import com.example.week2.adapters.ColorItemAdapter;
import com.example.week2.database.DataBaseHelper;
import com.example.week2.items.MyColors;
import com.example.week2.control.Router;
import com.example.week2.items.NoteItem;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class EditFragment extends Fragment {

    private NoteItem note;
    private boolean isEditMode;

    private static final String ARGUMENT_EDIT_MODE = "EditModeKey";

    @BindView(R.id.editFragmentEditTitleEditText)
    public EditText title;
    @BindView(R.id.editFragmentEditDecryptionEditText)
    public EditText description;
    public Unbinder unBinder = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_fragment, container, false);
        unBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        assert arguments != null;

        isEditMode = arguments.getBoolean(ARGUMENT_EDIT_MODE, false);

        if (isEditMode) {
            editMode();
            note = provideNoteItemById(1);
        } else {
            createMode();
        }
        updateBase();
    }

    private NoteItem provideNoteItemById(int noteItemId) {
        return NoteItem()
    }

    private void createMode() {
        helper.getNote().setColor("WHITE");
        LinearLayout mainPane = Objects.requireNonNull(getView()).findViewById(R.id.editFragmentMainPaneLinerLayout);
        mainPane.setBackgroundColor(MyColors.getColor(helper.getNote().getColor()));
        initBottomPane();
    }

    private void editMode() {

        View view = getView();
        assert view != null;
//        title =view.findViewById(R.id.editFragmentEditTitleEditText);
//        description =view.findViewById(R.id.editFragmentEditDecryptionEditText);

        title.setText(helper.getNote().getTitle());
        description.setText(helper.getNote().getDescription());
        LinearLayout mainPane = view.findViewById(R.id.editFragmentMainPaneLinerLayout);
        mainPane.setBackgroundColor(MyColors.getColor(helper.getNote().getColor()));
        initBottomPane();


    }

    private void initBottomPane() {
        RecyclerView colors = Objects.requireNonNull(getView()).findViewById(R.id.editFragmentColorPaletteRecycleView);
        ColorItemAdapter adapter = new ColorItemAdapter();
        adapter.setNote(helper.getNote());
        adapter.setColors(MyColors.getDataColors());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        colors.setAdapter(adapter);
        colors.setLayoutManager(layoutManager);


    }

    public void changeColor(String color) {
        LinearLayout mainPane = Objects.requireNonNull(getView()).findViewById(R.id.editFragmentMainPaneLinerLayout);
        mainPane.setBackgroundColor(MyColors.getColor(helper.getNote().getColor()));
        helper.getNote().setColor(color);
    }

    @Override
    public void onDestroy() {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        if (isEditMode) {
            if (helper.getNote().getDescription() == null && helper.getNote().getTitle() == null)
                dataBaseHelper.deleteNote(helper.getNote());
            else
                dataBaseHelper.upDate(helper.getNote());
        } else {
            if (helper.getNote().getDescription() != null || helper.getNote().getTitle() != null)
                dataBaseHelper.insert(helper.getNote());
        }
        Router router = new Router(getContext());
        router.reloadMainFragment();
        super.onDestroy();
        unBinder.unbind();

    }

    private void updateBase() {
        View view = getView();
        assert view != null;

        final EditText titleEditText = view.findViewById(R.id.editFragmentEditTitleEditText);
        final EditText description = view.findViewById(R.id.editFragmentEditDecryptionEditText);

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    helper.getNote().setDescription(description.getText().toString());
                } else helper.getNote().setDescription(null);
            }
        });
        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if (!text.toString().isEmpty()) {
                    helper.getNote().setTitle(titleEditText.getText().toString());
                } else helper.getNote().setTitle(null);
            }
        });
    }

    static public EditFragment newInstance(int id, boolean editMode) {

        Bundle args = new Bundle();

        args.putBoolean(ARGUMENT_EDIT_MODE, editMode);

        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
