package org.otfusion.caturday.application.dagger.component;

import org.otfusion.caturday.application.dagger.module.ActivityModule;
import org.otfusion.caturday.application.dagger.scope.ActivityScope;
import org.otfusion.caturday.view.caturday.activity.CaturdayActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent extends DaggerComponent {

    void inject(CaturdayActivity caturdayActivity);

}
