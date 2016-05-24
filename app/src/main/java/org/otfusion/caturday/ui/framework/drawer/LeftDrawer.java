package org.otfusion.caturday.ui.framework.drawer;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.otfusion.caturday.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeftDrawer implements Drawer {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.left_drawer)
    ListView mDrawerView;

    private final Context mContext;

    public LeftDrawer(Context context) {
        mContext = context;
    }

    @Override
    public void init(View view) {

        ButterKnife.bind(this, view);

        String[] drawerOptions = {
                "Start",
                "Favorites"
        };
        mDrawerView.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, drawerOptions));
    }

}
