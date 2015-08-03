package org.otfusion.votecats.ui.activities;

import android.widget.ListView;

import org.otfusion.votecats.R;

import butterknife.Bind;

public class FavoriteActivity extends CatActivity {

    @Bind(R.id.favorite_list_view)
    ListView _favoriteCats;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void loadUIElements() {
    }

}
