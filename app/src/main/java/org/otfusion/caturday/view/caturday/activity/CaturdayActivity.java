package org.otfusion.caturday.view.caturday.activity;

import android.content.Context;
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
import org.otfusion.caturday.application.dagger.component.ActivityComponent;
import org.otfusion.caturday.application.dagger.module.ActivityModule;
import org.otfusion.caturday.presenter.caturday.CaturdayPresenter;
import org.otfusion.caturday.ui.fragments.BaseDaggerFragment;
import org.otfusion.caturday.ui.fragments.FavoriteCatListFragment;
import org.otfusion.caturday.ui.fragments.FragmentFactory;
import org.otfusion.caturday.view.caturday.fragment.MainFragment;
import org.otfusion.caturday.view.caturday.CaturdayMvpView;
import org.otfusion.caturday.view.common.activity.BaseDaggerActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CaturdayActivity extends BaseDaggerActivity<ActivityComponent>
        implements CaturdayMvpView {

    private static final String NAVIGATION_DRAWER_STATE = "navigation_drawer_state";

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Inject
    CaturdayPresenter caturdayPresenter;
    private int selectedNavigationIndex = -1;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected ActivityComponent getDaggerComponent() {
        return getApplicationComponent().with(new ActivityModule(this));
    }

    @Override
    protected void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getDaggerComponent().inject(this);
        caturdayPresenter.attachView(this);
    }

    @Override
    protected void destroy() {
        caturdayPresenter.detachView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setSupportActionBar(mToolbar);
        loadNavigationDrawer();
        selectDrawerOption();
    }

    @Override
    protected void onDestroy() {
        destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Menu menu = mNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).isChecked()) {
                selectedNavigationIndex = i;
                break;
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            selectedNavigationIndex = savedInstanceState.getInt(NAVIGATION_DRAWER_STATE);
            selectDrawerOption();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(NAVIGATION_DRAWER_STATE, selectedNavigationIndex);
        super.onSaveInstanceState(outState);
    }

    private void selectDrawerOption() {
        Menu menu = mNavigationView.getMenu();

        MenuItem item = menu.findItem(R.id.navigation_first);
        if (selectedNavigationIndex >= 0) {
            item = menu.getItem(selectedNavigationIndex);
        }

        selectDrawerItem(item.getItemId());
    }

    private void loadFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseDaggerFragment fragment = (BaseDaggerFragment) fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = FragmentFactory.createFragment(tag);
        }

        if (MainFragment.FRAGMENT_TAG.equals(tag) && fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
            if (!MainFragment.FRAGMENT_TAG.equals(tag) && fragmentManager.getBackStackEntryCount() == 0) {
                fragmentTransaction.addToBackStack(tag);
            }
            fragmentTransaction.commit();
        }
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
            MenuItem firstItem = mNavigationView.getMenu().findItem(R.id.navigation_first);
            selectDrawerItem(firstItem.getItemId());
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void loadNavigationDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item.getItemId());
                return true;
            }
        });

        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void selectDrawerItem(int drawerItemId) {
        MenuItem menuItem = mNavigationView.getMenu().findItem(drawerItemId);
        String tag;
        switch (drawerItemId) {
            case R.id.navigation_first:
                tag = MainFragment.FRAGMENT_TAG;
                break;
            case R.id.navigation_second:
                tag = FavoriteCatListFragment.FRAGMENT_TAG;
                break;
            default:
                tag = MainFragment.FRAGMENT_TAG;
        }

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        loadFragment(tag);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}
