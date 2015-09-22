package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.otfusion.votecats.R;
import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.ui.adapters.FavoriteCatAdapter;

import java.util.List;

import butterknife.Bind;

public class FavoriteActivity extends CatActivity {

    @Bind(R.id.favorite_list_view)
    protected ListView _favoriteCatsView;

    private FavoriteCatAdapter _favoriteCatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshFavoriteCats();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void loadContent() {
        _favoriteCatAdapter = new FavoriteCatAdapter();
        _favoriteCatsView.setAdapter(_favoriteCatAdapter);
        registerForContextMenu(_favoriteCatsView);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite_context_delete:
                handleContextMenuDeleteOption(item);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void handleContextMenuDeleteOption(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        List<Cat> catFromsAdapter = getFavoriteCatAdapter().getCats();
        menuInfo = getAdapterContextMenuInfo(item.getMenuInfo());
        Cat favoritedCat = catFromsAdapter.get(menuInfo.position);
        getCatService().deleteFromFavorites(favoritedCat);
        refreshFavoriteCats();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorite_context_menu, menu);
    }

    private List<Cat> getFavoriteCats() {
        return getCatService().getFavoriteCats();
    }

    private void refreshFavoriteCats() {
        getFavoriteCatAdapter().updateCats(getFavoriteCats());
    }

    public FavoriteCatAdapter getFavoriteCatAdapter() {
        return _favoriteCatAdapter;
    }
}
