package org.otfusion.caturday.ui.activities;

import android.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.otfusion.caturday.R;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.ui.fragments.BaseFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatImageFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatListFragment;
import org.otfusion.caturday.ui.fragments.callbacks.FavoriteCallback;

import butterknife.Bind;

public class FavoriteActivity extends CatActivity implements FavoriteCallback {

    public static final String FAVORITE = "favorite";
    @Bind(R.id.favorite_toolbar)
    Toolbar mToolbar;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void loadUIContent() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable
                .abc_ic_ab_back_mtrl_am_alpha));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getFragmentManager().popBackStackImmediate()) {
                    onBackPressed();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showFragment(FavoriteCatListFragment.newInstance());
    }

    private void showFragment(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment, FAVORITE);
        fragmentTransaction.commit();
    }

    @Override
    public void showFavoritedCatImage(Cat cat) {
        BaseFragment fragment = FavoriteCatImageFragment.newInstance(cat);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(FAVORITE);
        fragmentTransaction.commit();
    }
}
