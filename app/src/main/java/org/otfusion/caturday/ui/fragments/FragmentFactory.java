package org.otfusion.caturday.ui.fragments;

import org.otfusion.caturday.view.caturday.fragment.MainFragment;

public final class FragmentFactory {

    private FragmentFactory() {

    }

    public static BaseDaggerFragment createFragment(String fragmentName) {
        switch(fragmentName) {
            case MainFragment.FRAGMENT_TAG:
                return MainFragment.newInstance();
            case FavoriteCatListFragment.FRAGMENT_TAG:
                return FavoriteCatListFragment.newInstance();
            default:
                return null;
        }
    }

}
