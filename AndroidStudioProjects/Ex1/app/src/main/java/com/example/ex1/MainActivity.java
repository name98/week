package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.ex1.fragments.MainFragment;
import com.example.ex1.fragments.MainFragment2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        //PasswordGenerating
        manager.beginTransaction()
                .add(R.id.container,new MainFragment2())
                .commit();
        //Counter
//        manager.beginTransaction()
//                .add(R.id.container,new MainFragment())
//                .commit();




    }
}
