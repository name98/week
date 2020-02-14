package com.example.week2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.R;
import com.example.week2.database.DataBaseHelper;
import com.example.week2.helpers.EditFragmentHelper;
import com.example.week2.items.MyColors;
import com.example.week2.items.NoteItem;
import com.example.week2.control.Router;

import java.util.ArrayList;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ItemHolder> {

    private ArrayList<NoteItem> notes=new ArrayList<>();
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.note_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        NoteItem note = notes.get(position);
        holder.setDecryption(note.getDescription());
        holder.setPaneColor(note.getColor());
        holder.setTitle(note.getTitle());
        holder.setPaneListener(position);
        holder.initPin(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(ArrayList<NoteItem> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView decryption;
        CardView pane;
        ImageView deletNote;
        ImageView pinNote;
        ImageView copyNote;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.noteItemTitleTextView);
            decryption=itemView.findViewById(R.id.noteItemDescriptionTextView);
            pane=itemView.findViewById(R.id.noteItemPaneCardView);
            deletNote = itemView.findViewById(R.id.noteItemDeleteNoteImageView);
            copyNote=itemView.findViewById(R.id.noteItemCopyNoteImageView);
            pinNote=itemView.findViewById(R.id.noteItemPinNoteImageView);
        }
        void setPaneColor(String color){
            pane.setCardBackgroundColor(MyColors.getColor(color));
            FrameLayout border = itemView.findViewById(R.id.noteItemBorderColorFrameLayout);
            border.setBackgroundColor(MyColors.getDarkColor(color));
        }
        void initPin(int id){
            pinNote.setImageResource(R.drawable.ic_unpined);
            if(notes.get(id).isSignificance())
                pinNote.setImageResource(R.drawable.ic_pined);
        }
        void setPaneListener(final int id){
            pane.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Router router = new Router(itemView.getContext());
                    EditFragmentHelper helper = new EditFragmentHelper();
                    helper.setMode(true);
                    helper.setNote(notes.get(id));
                    router.addEditFragment(helper);
                }
            });
            final DataBaseHelper helper = new DataBaseHelper(itemView.getContext());
            copyNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper.insert(notes.get(id));
                    reLoadFragment();
                }
            });
            deletNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper.deleteNote(notes.get(id));
                    reLoadFragment();
                }
            });
            pinNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteItem note = notes.get(id);
                    note.setSignificance(!notes.get(id).isSignificance());
                    helper.upDate(note);
                    reLoadFragment();
                }
            });
        }

        void setTitle(String text){
            title.setText(text);
        }
        void setDecryption(String text){
            decryption.setText(text);
        }

        void reLoadFragment(){
            Router router=new Router(itemView.getContext());
            router.reloadMainFragment();
        }
    }
}
