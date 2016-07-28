package org.otfusion.caturday.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import org.otfusion.caturday.R;
import org.otfusion.caturday.common.domain.Cat;
import org.otfusion.caturday.ui.fragments.BaseFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatImageFragment;
import org.otfusion.caturday.view.common.activity.CatActivity;

public class FavoriteImageActivity extends CatActivity {

    public static final String MODEL_KEY = "cat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_image);
        Bundle extras = getIntent().getExtras();
        Cat cat = (Cat) extras.get(MODEL_KEY);
        BaseFragment fragment = FavoriteCatImageFragment.newInstance(cat);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
