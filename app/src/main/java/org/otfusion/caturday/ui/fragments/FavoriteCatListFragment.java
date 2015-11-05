package org.otfusion.caturday.ui.fragments;


import android.app.Activity;
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
import org.otfusion.caturday.util.UIUtils;

import java.util.List;

import butterknife.Bind;

public class FavoriteCatListFragment extends BaseFragment {

    private FavoriteCallback callback;

    @Bind(R.id.favorite_list_view)
    ListView mFavoriteCatsView;

    private FavoriteCatAdapter mFavoriteCatAdapter;

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

            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite_context_delete:
                handleContextMenuDeleteOption(item);
                return true;
            case R.id.action_favorite_context_share:
                UIUtils.showToast("Soon");
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
        catService.deleteFromFavorites(favoritedCat);
        refreshFavoriteCats();
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
        getFavoriteCatAdapter().updateCats(getFavoriteCats());
    }

    public FavoriteCatAdapter getFavoriteCatAdapter() {
        return mFavoriteCatAdapter;
    }

}
