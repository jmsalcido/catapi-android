package org.otfusion.caturday.view.caturday;

import org.otfusion.caturday.view.MvpView;

public interface CaturdayMvpView extends MvpView {

    void loadNavigationDrawer();

    void selectDrawerItem(int drawerItemId);

}
