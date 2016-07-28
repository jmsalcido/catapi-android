package org.otfusion.caturday.view.common.activity;

import android.support.v7.app.AppCompatActivity;

import org.otfusion.caturday.application.dagger.ApplicationComponent;
import org.otfusion.caturday.application.CaturdayApplication;

public abstract class CatActivity extends AppCompatActivity {

    protected CaturdayApplication getApplicationInstance() {
        return (CaturdayApplication) getApplication();
    }

    protected ApplicationComponent getApplicationComponent() {
        return getApplicationInstance().getApplicationComponent();
    }
}
