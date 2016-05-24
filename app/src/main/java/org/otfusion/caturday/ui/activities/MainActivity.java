package org.otfusion.caturday.ui.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.otfusion.caturday.R;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.ui.fragments.BaseFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatImageFragment;
import org.otfusion.caturday.ui.fragments.MainFragment;
import org.otfusion.caturday.ui.fragments.callbacks.FavoriteCallback;

import butterknife.BindView;

public class MainActivity extends CatActivity implements FavoriteCallback {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadUIContent() {
        getDrawer().init(findViewById(android.R.id.content));
        setupToolbar();
        startFragment(MainFragment.newInstance());
    }

    private void setupToolbar() {
        mToolbar.setCollapsible(true);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void showFavoritedCatImage(Cat cat) {
        BaseFragment fragment = FavoriteCatImageFragment.newInstance(cat);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack("favorites");
        fragmentTransaction.replace(R.id.fragment_container, fragment, "image");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}
