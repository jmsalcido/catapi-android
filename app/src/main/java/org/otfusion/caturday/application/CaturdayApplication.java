package org.otfusion.caturday.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import org.otfusion.caturday.application.dagger.ApplicationComponent;
import org.otfusion.caturday.application.dagger.ApplicationModule;
import org.otfusion.caturday.application.dagger.DaggerApplicationComponent;

import io.fabric.sdk.android.Fabric;

public class CaturdayApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
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
