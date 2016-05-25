package org.otfusion.caturday.ui.activities;

import android.support.v7.widget.Toolbar;
import android.view.View;

import org.otfusion.caturday.R;
import org.otfusion.caturday.ui.fragments.FavoriteCatListFragment;

import butterknife.BindView;


@Deprecated
// this one is going to die
public class FavoriteActivity extends CatActivity {

    public static final String FAVORITE = "favorite";
    public static final String IMAGE = "image";
    @BindView(R.id.favorite_toolbar)
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
        startFragment(FavoriteCatListFragment.newInstance(), FAVORITE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
