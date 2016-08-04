package org.otfusion.caturday.application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class ReleaseCaturdayApplication extends CaturdayApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }
}
