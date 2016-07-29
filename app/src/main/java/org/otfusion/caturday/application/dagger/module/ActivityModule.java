package org.otfusion.caturday.application.dagger.module;

import org.otfusion.caturday.application.dagger.scope.ActivityScope;
import org.otfusion.caturday.presenter.caturday.CaturdayPresenter;
import org.otfusion.caturday.view.common.activity.BaseDaggerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final BaseDaggerActivity baseDaggerActivity;

    public ActivityModule(BaseDaggerActivity activity) {
        baseDaggerActivity = activity;
    }

    @ActivityScope
    @Provides
    CaturdayPresenter provideCaturdayPresenter() {
        return new CaturdayPresenter();
    }

}
