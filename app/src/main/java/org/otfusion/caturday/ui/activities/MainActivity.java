package org.otfusion.caturday.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.otfusion.caturday.R;
import org.otfusion.caturday.ui.fragments.MainFragment;
import org.otfusion.caturday.ui.framework.drawer.Drawer;
import org.otfusion.caturday.ui.framework.drawer.LeftDrawer;

import butterknife.BindView;

public class MainActivity extends CatActivity {

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
        // TODO: 5/23/16 move this to AppModule.
        Drawer drawer = new LeftDrawer(getApplicationContext());
        drawer.init(findViewById(android.R.id.content));
        setupToolbar();
        startFragment(MainFragment.newInstance());
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }
}
