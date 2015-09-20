package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import org.otfusion.votecats.R;
import org.otfusion.votecats.service.CatService;

import javax.inject.Inject;

import butterknife.Bind;

public class FavoriteActivity extends CatActivity {

    @Bind(R.id.favorite_list_view)
    ListView _favoriteCats;

    @Inject
    CatService _catService;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void loadUIElements() {
        setProgressBarIndeterminateVisibility(false);
    }

    private void loadListView() {
        _catService.getFavoriteCats();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(true);
    }
}
