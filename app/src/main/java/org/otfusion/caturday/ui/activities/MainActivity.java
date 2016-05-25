package org.otfusion.caturday.ui.activities;

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
import org.otfusion.caturday.ui.fragments.FavoriteCatListFragment;
import org.otfusion.caturday.ui.fragments.MainFragment;
import org.otfusion.caturday.ui.fragments.callbacks.ReplaceFragmentCallback;

import butterknife.BindView;

public class MainActivity extends CatActivity implements ReplaceFragmentCallback {

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
        startFragment(MainFragment.newInstance());
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
                BaseFragment fragment;
                switch (position) {
                    case 0:
                        fragment = MainFragment.newInstance();
                        break;
                    case 1:
                        fragment = FavoriteCatListFragment.newInstance();
                        break;
                    default:
                        throw new AssertionError("That selection is wrong");
                }
                replaceFragment(fragment);
                mDrawerLayout.closeDrawer(mDrawerView);
            }
        });
    }

    @Override
    public void replaceFragmentCallback(BaseFragment fragment) {
        this.replaceFragment(fragment);
    }
}
