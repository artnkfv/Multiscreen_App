package com.example.android.miwok;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


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
