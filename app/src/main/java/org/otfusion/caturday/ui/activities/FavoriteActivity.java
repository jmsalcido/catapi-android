package org.otfusion.caturday.ui.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcel;
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
                onBackPressed();
            }
        });
        showFragment(getUsedFragment());
    }

    private void showFragment(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment, FAVORITE);
        fragmentTransaction.commit();
    }

    BaseFragment getUsedFragment() {
        // should add logic here to check activity status/current fragment in case of being
        // destroyed, so we can return the used fragment.
        // or from a service?
        return new FavoriteCatListFragment();
    }

    @Override
    public void showFavoritedCatImage(Cat cat) {
        // TODO show FavoritedCatImage fragment
        // the UsedFragment should be a ListFragment if not, do not display the image! ;)
        BaseFragment fragment = FavoriteCatImageFragment.newInstance(cat);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
