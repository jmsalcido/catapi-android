package org.otfusion.caturday.view.caturday.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CaturdayFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final int NUMBER_OF_FRAGMENTS_IN_MAIN = 2;

    public CaturdayFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS_IN_MAIN;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
