package com.example.week2.adapters;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.R;
import com.example.week2.items.MyColors;
import com.example.week2.items.NoteItem;
import com.example.week2.control.Router;

import java.util.ArrayList;

public class ColorItemAdapter extends RecyclerView.Adapter<ColorItemAdapter.ItemHolder> {
    private NoteItem note;
    private ArrayList<String> colors;

    public void setNote(NoteItem note) {
        this.note = note;
        //notifyDataSetChanged();
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.color_item,
                        parent,
                        false
                ));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        String color = colors.get(position);
        holder.initValues(color);
        holder.setColorListener(color);

    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        ImageView done;
        FrameLayout colorFrame;
        ItemHolder(@NonNull View itemView) {
            super(itemView);
            done =itemView.findViewById(R.id.colorItemDoneImageView);
            colorFrame=itemView.findViewById(R.id.colorItemFrameColorFrameLayout);
        }
        void initValues(String color){
            Drawable background = colorFrame.getBackground();
            done.setVisibility(View.INVISIBLE);
            if(background instanceof ShapeDrawable){
                ShapeDrawable shapeDrawable = (ShapeDrawable) background;
                shapeDrawable.getPaint().setColor(MyColors.getColor(color));
            }
            else if (background instanceof GradientDrawable) {
                GradientDrawable gradientDrawable = (GradientDrawable) background;
                gradientDrawable.setColor(MyColors.getColor(color));
            }
            if(color.equals(note.getColor())){
                done.setVisibility(View.VISIBLE);
            }
        }
        void setColorListener(final String color){
            colorFrame.setOnClickListener(v -> {
                note.setColor(color);
                sendMessageToFragment(color);
                notifyDataSetChanged();

            });
        }
        void sendMessageToFragment(String color){
            Router router = new Router(itemView.getContext());
            router.changeColorInEditFragment(color);
        }

    }
}
