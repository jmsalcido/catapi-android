package org.otfusion.votecats.application;

import android.app.Application;

public class VoteCatsApplication extends Application {

    private ApplicationComponent _applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        _applicationComponent = DaggerApplicationComponent.builder()
                .voteCatsModule(new VoteCatsModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return _applicationComponent;
    }
}
