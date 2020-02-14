package com.example.ex1.ex2;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex1.MainActivity;
import com.example.ex1.R;

import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ItemHolder>  {
    private ArrayList<String> passwords=new ArrayList<>();

    public itemAdapter(ArrayList<String> passwords) {
        this.passwords = passwords;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.setTextView(passwords.get(position));
        holder.setButton(passwords.get(position));

    }

    @Override
    public int getItemCount() {
        return passwords.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private Button button;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.generatedPassword);
            button = itemView.findViewById(R.id.copyPass);
        }

        public void setTextView(String s) {
            textView.setText(s);
        }

        public void setButton(final String s) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager clipboardManager = (ClipboardManager) ((MainActivity) v
                            .getContext())
                            .getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("text",s);
                    clipboardManager.setPrimaryClip(clip);
                    Toast toast = Toast.makeText((MainActivity) v.getContext(),"Успешно скопировано: " + s,Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }
}
