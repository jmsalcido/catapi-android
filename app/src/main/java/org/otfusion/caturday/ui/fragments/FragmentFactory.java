package org.otfusion.caturday.ui.fragments;

public final class FragmentFactory {

    private FragmentFactory() {

    }

    public static BaseDaggerFragment createFragment(String fragmentName) {
        switch(fragmentName) {
            case MainDaggerFragment.FRAGMENT_TAG:
                return MainDaggerFragment.newInstance();
            case FavoriteCatListDaggerFragment.FRAGMENT_TAG:
                return FavoriteCatListDaggerFragment.newInstance();
            default:
                return null;
        }
    }

}
