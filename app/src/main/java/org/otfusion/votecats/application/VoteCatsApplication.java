package org.otfusion.votecats.application;

import android.app.Application;

public class VoteCatsApplication extends Application {

    private ApplicationComponent _applicationComponent;
    private static VoteCatsApplication _app;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        _app = this;
    }

    private void initializeInjector() {
        _applicationComponent = DaggerApplicationComponent.builder()
                .voteCatsModule(new VoteCatsModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return _applicationComponent;
    }

    public static VoteCatsApplication getContext() {
        return _app;
    }
}
