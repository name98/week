package com.example.week2.control;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import com.example.week2.MainActivity;
import com.example.week2.R;
import com.example.week2.fragments.EditFragment;
import com.example.week2.fragments.MainFragment;
import com.example.week2.helpers.EditFragmentHelper;


public class Router {

    private FragmentManager manager;

    public Router(Context context){
        manager = ((MainActivity) context).getSupportFragmentManager();
    }

    public void addMainFragment() {
        MainFragment mainFragment  = new MainFragment();
        manager.beginTransaction()
                .add(R.id.mainActivityContainerFrameLayout,mainFragment,"MainFragment")
                .commit();
    }

    public void addEditFragment(EditFragmentHelper.class helper){
        EditFragment editFragment = EditFragment.newInstance(1, true);
        manager.beginTransaction()
                .add(R.id.mainActivityContainerFrameLayout,editFragment,"EditFragment")
                .addToBackStack(null)
                .commit();
    }

    public void changeColorInEditFragment(String color){
        EditFragment editFragment = (EditFragment) manager.findFragmentByTag("EditFragment");
        assert editFragment != null;
        editFragment.changeColor(color);
    }

    public void reloadMainFragment(){
        MainFragment mainFragment = (MainFragment) manager.findFragmentByTag("MainFragment");
        assert mainFragment != null;
        manager.beginTransaction()
                .detach(mainFragment)
                .attach(mainFragment)
                .commit();

    }


}
