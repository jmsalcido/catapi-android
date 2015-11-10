package org.otfusion.caturday.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.ui.adapters.FavoriteCatAdapter;
import org.otfusion.caturday.ui.fragments.callbacks.FavoriteCallback;
import org.otfusion.caturday.util.ApplicationUtils;
import org.otfusion.caturday.util.FileUtils;

import java.util.List;

import butterknife.Bind;

public class FavoriteCatListFragment extends BaseFragment {

    private FavoriteCallback callback;

    @Bind(R.id.favorite_list_view)
    ListView mFavoriteCatsView;

    private FavoriteCatAdapter mFavoriteCatAdapter;

    public static FavoriteCatListFragment newInstance() {
        return new FavoriteCatListFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        VoteCatsApplication voteCatsApplication = ApplicationUtils.getApplication(activity);
        voteCatsApplication.getApplicationComponent().inject(this);
        callback = ApplicationUtils.castActivityToInterface(activity, FavoriteCallback.class);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_favorite_cat_list;
    }

    @Override
    public void loadUIContent() {
        mFavoriteCatAdapter = new FavoriteCatAdapter();
        mFavoriteCatsView.setAdapter(mFavoriteCatAdapter);
        refreshFavoriteCats();
        registerForContextMenu(mFavoriteCatsView);

        mFavoriteCatsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.showFavoritedCatImage(mFavoriteCatAdapter.getItem(position));
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite_context_delete:
                return handleContextMenuDeleteOption(item);
            case R.id.action_favorite_context_share:
                return handleContextMenuShareOption(item);
            default:
                return super.onContextItemSelected(item);
        }
    }

    private boolean handleContextMenuDeleteOption(MenuItem item) {
        Cat favoritedCat = getObjectForMenuItem(item);
        catService.deleteFromFavorites(favoritedCat);
        refreshFavoriteCats();
        return true;
    }

    private Cat getObjectForMenuItem(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        List<Cat> catFromsAdapter = mFavoriteCatAdapter.getCats();
        menuInfo = getAdapterContextMenuInfo(item.getMenuInfo());
        return catFromsAdapter.get(menuInfo.position);
    }

    private boolean handleContextMenuShareOption(MenuItem item) {
        Cat cat = getObjectForMenuItem(item);
        Intent shareImageIntent = ApplicationUtils.getShareImageIntent(cat);
        startActivity(Intent.createChooser(shareImageIntent, "Share a cat!"));
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.favorite_context_menu, menu);
    }

    private List<Cat> getFavoriteCats() {
        return catService.getFavoriteCats();
    }

    private void refreshFavoriteCats() {
        mFavoriteCatAdapter.updateCats(getFavoriteCats());
    }

}
