package com.example.ex1.ex1;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ex1.R;

public class Counter {
    private View view;
    private int counter = 0;
    public Counter(View view) {
        this.view = view;
        start();
    }

    private void start(){
        final EditText text = view.findViewById(R.id.counter);
        text.setKeyListener(null);
        Button button = view.findViewById(R.id.bt1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                text.setText(String.valueOf(counter));
            }
        });
    }

}
