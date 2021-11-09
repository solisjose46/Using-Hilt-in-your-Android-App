package com.example.javahiltdemo.navigator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.javahiltdemo.R;
import com.example.javahiltdemo.ui.ButtonsFragment;
import com.example.javahiltdemo.ui.LogsFragment;

public class AppNavigatorImpl implements AppNavigator{
    private FragmentActivity activity;

    public AppNavigatorImpl(FragmentActivity activity){
        this.activity = activity;
    }

    @Override
    public void navigateTo(Screens screen){
        Fragment fragment = new Fragment();
        switch (screen){
            case BUTTONS:
                fragment = new ButtonsFragment();
                break;
            case LOGS:
                fragment = new LogsFragment();
                break;
        }
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(String.valueOf(fragment.getClass())).commit();
    }
}
