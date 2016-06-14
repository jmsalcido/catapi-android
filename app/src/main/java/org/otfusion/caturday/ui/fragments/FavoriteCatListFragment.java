package org.otfusion.caturday.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.ui.activities.FavoriteImageActivity;
import org.otfusion.caturday.ui.adapters.DividerItemDecoration;
import org.otfusion.caturday.ui.adapters.FavoriteCatAdapter;
import org.otfusion.caturday.ui.adapters.OnItemClickListener;
import org.otfusion.caturday.util.ApplicationUtils;
import org.otfusion.caturday.util.FileUtils;
import org.otfusion.caturday.util.UIUtils;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;


public class FavoriteCatListFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = "favorite";

    @BindView(R.id.list_view)
    RecyclerView mFavoritedView;

    private RecyclerView.LayoutManager mFavoritedLayoutManager;
    private FavoriteCatAdapter mAdapter;

    public static FavoriteCatListFragment newInstance() {
        return new FavoriteCatListFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        VoteCatsApplication voteCatsApplication = ApplicationUtils.getApplication(activity);
        voteCatsApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_favorite_list;
    }

    @Override
    public void loadUIContent() {
        mFavoritedLayoutManager = new LinearLayoutManager(getContext());
        mFavoritedView.setLayoutManager(mFavoritedLayoutManager);
//        mFavoritedView.setHasFixedSize(true);
        registerForContextMenu(mFavoritedView);
        mAdapter = new FavoriteCatAdapter(getAdapterClickListener());
        mAdapter.updateCats(getCatService().getFavoriteCats());
        mFavoritedView.setAdapter(mAdapter);
        mFavoritedView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
    }

    private OnItemClickListener<Cat> getAdapterClickListener() {
        return new OnItemClickListener<Cat>() {
            @Override
            public void onItemClick(Cat item) {
                Intent intent = new Intent(getContext(), FavoriteImageActivity.class);
                intent.putExtra(FavoriteImageActivity.MODEL_KEY, item);
                startActivity(intent);
            }
        };
    }

    @Override
    public int getTitleId() {
        return R.string.title_activity_favorite;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.favorite_context_menu, menu);
    }

    private void refreshFavoriteCats() {
        mAdapter.updateCats(getCatService().getFavoriteCats());
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
        List<Cat> catFromsAdapter = Collections.emptyList();
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

}
