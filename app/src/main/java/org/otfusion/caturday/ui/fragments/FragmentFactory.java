package org.otfusion.caturday.ui.fragments;

public final class FragmentFactory {

    private FragmentFactory() {

    }

    public static BaseFragment createFragment(String fragmentName) {
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
