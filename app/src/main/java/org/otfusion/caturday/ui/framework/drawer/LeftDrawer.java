package org.otfusion.caturday.ui.framework.drawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.ui.activities.CatActivity;
import org.otfusion.caturday.ui.fragments.BaseFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatListFragment;
import org.otfusion.caturday.ui.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeftDrawer implements Drawer {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.left_drawer)
    ListView mDrawerView;

    private final CatActivity mActivity;

    public LeftDrawer(CatActivity activity) {
        mActivity = activity;
    }

    @Override
    public void init(View view) {

        ButterKnife.bind(this, view);

        String[] drawerOptions = {
                "Start",
                "Favorites"
        };

        Context context = mActivity.getApplicationContext();
        mDrawerView.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, drawerOptions));
        mDrawerView.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        public static final int START_FRAGMENT = 0;
        public static final int FAVORITES_FRAGMENT = 1;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {
            FragmentManager fragmentManager = mActivity.getFragmentManager();
            BaseFragment fragment;
            String tag;
            switch (position) {
                case START_FRAGMENT:
                    fragment = MainFragment.newInstance();
                    tag = "main";
                    break;
                case FAVORITES_FRAGMENT:
                    fragment = FavoriteCatListFragment.newInstance();
                    tag = "favorite";
                    break;
                default:
                    throw new AssertionError("Should not come here.");
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, tag).commit();
            mDrawerLayout.closeDrawer(mDrawerView);
        }
    }

}
