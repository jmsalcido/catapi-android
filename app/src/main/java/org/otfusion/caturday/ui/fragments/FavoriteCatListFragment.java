package org.otfusion.caturday.ui.fragments;


import android.app.Activity;
import android.widget.ListView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.ui.fragments.callbacks.FavoriteCallback;
import org.otfusion.caturday.util.ApplicationUtils;

import butterknife.Bind;

public class FavoriteCatListFragment extends BaseFragment {

    private FavoriteCallback callback;

    @Bind(R.id.favorite_list_view)
    ListView mListView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = ApplicationUtils.castActivityToInterface(activity, FavoriteCallback.class);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_favorite_cat_list;
    }

    @Override
    public void loadUIContent() {

    }
}
