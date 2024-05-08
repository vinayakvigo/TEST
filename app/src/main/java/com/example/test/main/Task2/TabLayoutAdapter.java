package com.example.test.main.Task2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.test.main.Task2.Fragments.FristFragment;
import com.example.test.main.Task2.Fragments.SecondFragment;
import com.example.test.main.Task2.Fragments.ThirdFragment;

public class TabLayoutAdapter extends FragmentPagerAdapter {

    FristFragment firstFragment;
    SecondFragment secondFragment;
    ThirdFragment thirdFragment;
    public TabLayoutAdapter(@NonNull FragmentManager fm, Bundle bundle) {
        super(fm);

         firstFragment = new FristFragment();
        firstFragment.setArguments(bundle);

         secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);

         thirdFragment = new ThirdFragment();
        thirdFragment.setArguments(bundle);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return firstFragment;
            case 1:
                return secondFragment;
            case 2:
                return thirdFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Number of tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "First";
            case 1:
                return "Second";
            case 2:
                return "Third";
            default:
                return null;
        }
    }
}

