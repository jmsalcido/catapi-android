package org.otfusion.caturday.application;

import android.app.Application;

import org.otfusion.caturday.application.dagger.component.ApplicationComponent;
import org.otfusion.caturday.application.dagger.component.DaggerApplicationComponent;
import org.otfusion.caturday.application.dagger.module.ApplicationModule;

public class CaturdayApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
