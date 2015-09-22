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
    ListView _favoriteCats;

    private final FavoriteCatAdapter _favoriteCatAdapter = new FavoriteCatAdapter();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void loadContent() {
        _favoriteCats.setAdapter(_favoriteCatAdapter);
        registerForContextMenu(_favoriteCats);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        switch (item.getItemId()) {
            case R.id.action_favorite_context_delete:
                List<Cat> favoriteCats = getFavoriteCats();
                menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                Cat cat = favoriteCats.get(menuInfo.position);
                getCatService().deleteFromFavorites(cat);
                updateCats();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateCats();
    }

    private void updateCats() {
        _favoriteCatAdapter.updateCats(getFavoriteCats());
    }
}
