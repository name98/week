package com.example.ex1.ex2;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex1.R;

import java.util.ArrayList;

public class PasswordGenerator {private View view;
    private boolean ABC = false;
    private boolean abc = false;
    private boolean num = true;
    private boolean sym = false;
    private int length = 6;

    public PasswordGenerator(View view) {
        this.view = view;
        start();
    }
    private void start(){
        initSwitchers();
        final EditText counter = view.findViewById(R.id.sizePass);
        counter.setKeyListener(null);
        Button minus = view.findViewById(R.id.minus);
        minus.setEnabled(false);
        Button plus= view.findViewById(R.id.plus);
        Button Generate = view.findViewById(R.id.goToGenerate);
        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> strings = new ArrayList<>();
                for(int i=0;i<10;i++){
                    strings.add(new Generator(num,abc,ABC,sym,length).getResult());
                }
                RecyclerView recyclerView = view.findViewById(R.id.recView);
                LinearLayoutManager manager =new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
                itemAdapter adapter = new itemAdapter(strings);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(length!=6){
                    length--;
                    counter.setText(String.valueOf(length));
                }

                buttonActive();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(length!=12){
                    length++;
                    counter.setText(String.valueOf(length));
                }
                buttonActive();
            }
        });

    }
    private void initSwitchers(){
        Switch ABCSwitcher=view.findViewById(R.id.ABC);
        Switch abcSwitcher=view.findViewById(R.id.abc);
        Switch numSwitcher=view.findViewById(R.id.num);
        Switch symSwitcher=view.findViewById(R.id.sym);
        numSwitcher.setEnabled(false);
        ABCSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchActive("ABC",isChecked);
            }
        });
        abcSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchActive("abc",isChecked);
            }
        });
        numSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchActive("num",isChecked);
            }
        });
        symSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchActive("sym",isChecked);
            }
        });
    }
    private void buttonActive(){
        Button minus = view.findViewById(R.id.minus);
        Button plus = view.findViewById(R.id.plus);
        if(length==6){

            minus.setEnabled(false);
        }
        else minus.setEnabled(true);
        if(length==12){

            plus.setEnabled(false);
        }
        else plus.setEnabled(true);

    }
    private void otherIsFalse(){
        Switch abcS = view.findViewById(R.id.abc);
        Switch ABCS = view.findViewById(R.id.ABC);
        Switch numS = view.findViewById(R.id.num);
        Switch symS = view.findViewById(R.id.sym);
        abcS.setEnabled(true);
        ABCS.setEnabled(true);
        numS.setEnabled(true);
        symS.setEnabled(true);
        if(abc && !ABC && !num && !sym){
            abcS.setEnabled(false);
            ABCS.setEnabled(true);
            numS.setEnabled(true);
            symS.setEnabled(true);
        }

        if(!abc && ABC && !num && !sym){
            abcS.setEnabled(true);
            ABCS.setEnabled(false);
            numS.setEnabled(true);
            symS.setEnabled(true);
        }


        if(!abc && !ABC && num && !sym)
        {
            ABCS.setEnabled(true);
            abcS.setEnabled(true);
            numS.setEnabled(false);

            symS.setEnabled(true);

        }
        if(!abc && !ABC && !num && sym)
        {
            abcS.setEnabled(true);
            ABCS.setEnabled(true);
            numS.setEnabled(true);

            symS.setEnabled(false);
        }

    }
    private void switchActive(String bool, boolean flag){
        Switch abcS = view.findViewById(R.id.abc);
        Switch ABCS = view.findViewById(R.id.ABC);
        Switch numS = view.findViewById(R.id.num);
        Switch symS = view.findViewById(R.id.sym);
        switch (bool){
            case "ABC":
                ABC=flag;
                otherIsFalse();
                break;
            case "abc":
                abc=flag;
                otherIsFalse();
                break;
            case "num":
                num=flag;
                otherIsFalse();
                break;
            case "sym":
                sym=flag;
                otherIsFalse();
                break;


        }
    }
    private void enableSw(){
        Switch abcS = view.findViewById(R.id.abc);
        Switch ABCS = view.findViewById(R.id.ABC);
        Switch numS = view.findViewById(R.id.num);
        Switch symS = view.findViewById(R.id.sym);
        abcS.setEnabled(true);
        ABCS.setEnabled(true);
        numS.setEnabled(true);
        symS.setEnabled(true);
    }
}
