package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.widget.ListView;

import org.otfusion.votecats.R;
import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.ui.adapters.FavoriteCatAdapter;

import java.util.List;

import butterknife.Bind;

public class FavoriteActivity extends CatActivity {

    @Bind(R.id.favorite_list_view)
    ListView _favoriteCats;

    private final FavoriteCatAdapter _favoriteCatAdapter = new FavoriteCatAdapter();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void loadContent() {
        _favoriteCats.setAdapter(_favoriteCatAdapter);
    }

    private List<Cat> getFavoriteCats() {
        return getCatService().getFavoriteCats();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _favoriteCatAdapter.updateCats(getFavoriteCats());
    }
}
