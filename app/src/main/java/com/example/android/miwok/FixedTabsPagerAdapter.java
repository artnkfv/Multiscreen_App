package com.example.android.miwok;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class FixedTabsPagerAdapter extends FragmentPagerAdapter {
    public FixedTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return  new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return MainActivity.getContext().getString(R.string.numbers_title);
            case 1:
                return MainActivity.getContext().getString(R.string.family_title);
            case 2:
                return  MainActivity.getContext().getString(R.string.colors_title);
            case 3:
                return  MainActivity.getContext().getString(R.string.phrases_title);
            default:
                return null;
        }
    }


}
