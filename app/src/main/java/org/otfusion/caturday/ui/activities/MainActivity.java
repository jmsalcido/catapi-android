package org.otfusion.caturday.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.otfusion.caturday.R;
import org.otfusion.caturday.ui.fragments.BaseFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatListFragment;
import org.otfusion.caturday.ui.fragments.FragmentFactory;
import org.otfusion.caturday.ui.fragments.MainFragment;
import org.otfusion.caturday.ui.fragments.callbacks.ReplaceFragmentCallback;

import butterknife.BindView;

public class MainActivity extends CatActivity implements ReplaceFragmentCallback {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation)
    NavigationView mNavigationView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    ActionBarDrawerToggle mActionBarDrawerToggle;

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
    }

    private void setupDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });

        selectMainFragment();
        mActionBarDrawerToggle.syncState();
    }

    private void selectMainFragment() {
        Menu menu = mNavigationView.getMenu();
        MenuItem item = menu.findItem(R.id.navigation_first);
        selectDrawerItem(item);
    }

    private void selectDrawerItem(MenuItem menuItem) {
        String tag;
        switch (menuItem.getItemId()) {
            case R.id.navigation_first:
                tag = MainFragment.FRAGMENT_TAG;
                break;
            case R.id.navigation_second:
                tag = FavoriteCatListFragment.FRAGMENT_TAG;
                break;
            default:
                tag = MainFragment.FRAGMENT_TAG;
        }

        BaseFragment fragment =  FragmentFactory.createFragment(tag);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (!MainFragment.FRAGMENT_TAG.equals(tag) && fragmentManager.getBackStackEntryCount() == 0) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            selectMainFragment();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void replaceFragmentCallback(BaseFragment fragment) {
        this.replaceFragment(fragment, "favorite", true);
    }
}
