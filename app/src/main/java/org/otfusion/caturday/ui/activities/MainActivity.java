package org.otfusion.caturday.ui.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.ui.fragments.BaseFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatImageFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatListFragment;
import org.otfusion.caturday.ui.fragments.FragmentFactory;
import org.otfusion.caturday.ui.fragments.MainFragment;
import org.otfusion.caturday.ui.fragments.callbacks.ReplaceFragmentCallback;

import butterknife.BindView;

public class MainActivity extends CatActivity implements ReplaceFragmentCallback {

    public static final int MAIN_FRAGMENT_DRAWER_POSITION = 0;
    public static final int FAVORITE_FRAGMENT_DRAWER_POSITION = 1;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.left_drawer)
    ListView mDrawerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadUIContent() {
        setSupportActionBar(mToolbar);
        setupDrawer();
        replaceFragment(MainFragment.newInstance(), MainFragment.FRAGMENT_TAG, false);
    }

    private void setupDrawer() {
        String[] drawerOptions = {
                "Start",
                "Favorites"
        };

        mDrawerView.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, drawerOptions));
        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name,
                        R.string.app_name);

        mDrawerLayout.setDrawerListener(drawerToggle);
        mDrawerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moveOrReplaceFragment(position);
                mDrawerLayout.closeDrawer(mDrawerView);
            }
        });
    }

    private void moveOrReplaceFragment(int position) {
        String tag;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (position) {
            case MAIN_FRAGMENT_DRAWER_POSITION:
                tag = MainFragment.FRAGMENT_TAG;
                getFragmentManager().popBackStack();
                break;
            case FAVORITE_FRAGMENT_DRAWER_POSITION:
                tag = FavoriteCatListFragment.FRAGMENT_TAG;
                fragmentTransaction.addToBackStack(null);
                break;
            default:
                throw new AssertionError("That selection is wrong");
        }
        BaseFragment fragment = getOrCreateFragment(tag);
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
    }

    private BaseFragment getOrCreateFragment(String fragmentName) {
        BaseFragment fragment = (BaseFragment) getFragmentManager().findFragmentByTag(fragmentName);
        if (fragment == null) {
            fragment = FragmentFactory.createFragment(fragmentName);
        }
        return fragment;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void replaceFragmentCallback(BaseFragment fragment) {
        this.replaceFragment(fragment, "favorite", true);
    }
}
