package org.otfusion.caturday.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.ui.activities.CatActivity;
import org.otfusion.caturday.ui.adapters.FavoriteCatAdapter;
import org.otfusion.caturday.ui.fragments.callbacks.ReplaceFragmentCallback;
import org.otfusion.caturday.util.ApplicationUtils;
import org.otfusion.caturday.util.FileUtils;
import org.otfusion.caturday.util.UIUtils;

import java.util.List;

import butterknife.BindView;


public class FavoriteCatListFragment extends BaseFragment {

    private ReplaceFragmentCallback callback;

    @BindView(R.id.favorite_list_view)
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
        callback = ApplicationUtils.castActivityToInterface(activity, ReplaceFragmentCallback.class);
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
                FavoriteCatImageFragment fragment = FavoriteCatImageFragment.newInstance(mFavoriteCatAdapter.getItem(position));
                callback.replaceFragmentCallback(fragment);
            }
        });
    }

    @Override
    public int getTitleId() {
        return R.string.title_activity_favorite;
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
        String filePath = FileUtils.getFileName(cat, true);
        if (filePath.isEmpty()) {
            UIUtils.showToast("Could not retrieve the image, try saving it again.");
            return false;
        } else {
            Intent shareImageIntent = ApplicationUtils.getShareImageIntent(Uri.parse(filePath));
            startActivity(Intent.createChooser(shareImageIntent, "Share a cat!"));
            return true;
        }
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
