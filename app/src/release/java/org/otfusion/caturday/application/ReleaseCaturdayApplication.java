package org.otfusion.caturday.application;

import com.crashlytics.android.Crashlytics;

import org.otfusion.caturday.BuildConfig;

import io.fabric.sdk.android.Fabric;

public class ReleaseCaturdayApplication extends CaturdayApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setupCrashlytics();
    }

    private void setupCrashlytics() {
        Fabric.with(this, new Crashlytics());
        Crashlytics.setString("GIT_SHA_KEY", BuildConfig.GIT_SHA);
        Crashlytics.setString("BUILD_TIME_KEY", BuildConfig.BUILD_TIME);
    }
}
