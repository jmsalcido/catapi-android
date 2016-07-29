package org.otfusion.caturday.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import org.otfusion.caturday.R;
import org.otfusion.caturday.application.dagger.component.ApplicationComponent;
import org.otfusion.caturday.common.domain.Cat;
import org.otfusion.caturday.ui.fragments.BaseDaggerFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatImageDaggerFragment;
import org.otfusion.caturday.view.common.activity.BaseDaggerActivity;

public class FavoriteImageActivity extends BaseDaggerActivity<ApplicationComponent> {

    public static final String MODEL_KEY = "cat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        Bundle extras = getIntent().getExtras();
        Cat cat = (Cat) extras.get(MODEL_KEY);
        BaseDaggerFragment fragment = FavoriteCatImageDaggerFragment.newInstance(cat);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected ApplicationComponent getDaggerComponent() {
        return getApplicationComponent();
    }

    @Override
    protected void init() {
        setContentView(R.layout.activity_favorite_image);
    }

    @Override
    protected void destroy() {
    }
}
