package org.otfusion.caturday.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.otfusion.caturday.R;
import org.otfusion.caturday.view.caturday.CaturdayMvpView;
import org.otfusion.caturday.view.caturday.activity.CaturdayActivity;
import org.otfusion.caturday.view.caturday.adapter.CaturdayFragmentPagerAdapter;

import butterknife.BindView;

public class NewMainActivity extends CaturdayActivity implements CaturdayMvpView {

    private CaturdayFragmentPagerAdapter mCaturdayFragmentPagerAdapter;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        mCaturdayFragmentPagerAdapter = new CaturdayFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mCaturdayFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
