package org.otfusion.caturday.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.otfusion.caturday.R;
import org.otfusion.caturday.application.dagger.component.ActivityComponent;
import org.otfusion.caturday.application.dagger.module.ActivityModule;
import org.otfusion.caturday.view.caturday.NewMainMvpView;
import org.otfusion.caturday.view.caturday.adapter.CaturdayFragmentPagerAdapter;
import org.otfusion.caturday.view.common.activity.BaseDaggerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewMainActivity extends BaseDaggerActivity<ActivityComponent> implements NewMainMvpView {

    private CaturdayFragmentPagerAdapter mCaturdayFragmentPagerAdapter;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        ButterKnife.bind(this);
        mCaturdayFragmentPagerAdapter = new CaturdayFragmentPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mCaturdayFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected ActivityComponent getDaggerComponent() {
        return getApplicationInstance().getApplicationComponent().with(new ActivityModule(this));
    }

    @Override
    protected void init() {

    }

    @Override
    protected void destroy() {

    }
}
