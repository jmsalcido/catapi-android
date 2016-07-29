package org.otfusion.caturday.view.common.activity;

import android.support.v7.app.AppCompatActivity;

import org.otfusion.caturday.application.CaturdayApplication;
import org.otfusion.caturday.application.dagger.component.ApplicationComponent;
import org.otfusion.caturday.application.dagger.component.DaggerComponent;

public abstract class BaseDaggerActivity<C extends DaggerComponent> extends AppCompatActivity {

    protected CaturdayApplication getApplicationInstance() {
        return (CaturdayApplication) getApplication();
    }

    protected ApplicationComponent getApplicationComponent() {
        return getApplicationInstance().getApplicationComponent();
    }

    protected abstract C getDaggerComponent();

    protected abstract void init();

    protected abstract void destroy();

}
