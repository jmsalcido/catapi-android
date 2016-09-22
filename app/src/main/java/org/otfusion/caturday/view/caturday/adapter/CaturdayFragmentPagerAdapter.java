package org.otfusion.caturday.view.caturday.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.otfusion.caturday.ui.fragments.BaseDaggerFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatListFragment;
import org.otfusion.caturday.ui.fragments.FragmentFactory;
import org.otfusion.caturday.view.caturday.fragment.MainFragment;

public class CaturdayFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final int NUMBER_OF_FRAGMENTS_IN_MAIN = 2;
    private final Context context;

    public CaturdayFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = FragmentFactory.createFragment(MainFragment.FRAGMENT_TAG);
                break;
            case 1:
                fragment = FragmentFactory.createFragment(FavoriteCatListFragment.FRAGMENT_TAG);
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
        BaseDaggerFragment fragment = (BaseDaggerFragment) getItem(position);
        return context.getString(fragment.getTitleId());
    }
}
