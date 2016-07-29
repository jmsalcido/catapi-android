package org.otfusion.caturday.application.dagger.component;

import org.otfusion.caturday.application.dagger.module.ActivityModule;
import org.otfusion.caturday.application.dagger.module.ApplicationModule;
import org.otfusion.caturday.ui.fragments.BaseDaggerFragment;
import org.otfusion.caturday.view.caturday.activity.CaturdayActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent extends DaggerComponent {
    void inject(BaseDaggerFragment fragment);

    ActivityComponent with(ActivityModule module);
}
