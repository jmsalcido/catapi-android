package org.otfusion.caturday.ui.fragments;

import android.app.Activity;
import android.os.Bundle;

import org.otfusion.caturday.R;

public class FavoriteCatImageFragment extends BaseFragment  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_favorite_cat_image;
    }

    @Override
    public void loadUIContent() {

    }
}
