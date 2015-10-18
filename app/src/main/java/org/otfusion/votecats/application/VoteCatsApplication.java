package org.otfusion.votecats.application;

import android.app.Application;

public class VoteCatsApplication extends Application {

    private ApplicationComponent applicationComponent;
    private static VoteCatsApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        this.app = this;
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static VoteCatsApplication getContext() {
        return app;
    }
}
