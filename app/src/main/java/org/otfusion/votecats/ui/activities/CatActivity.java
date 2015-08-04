package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;

import org.otfusion.votecats.application.VoteCatsModule;
import org.otfusion.votecats.application.ApplicationComponent;
import org.otfusion.votecats.application.VoteCatsApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

public abstract class CatActivity extends AppCompatActivity {

    @Inject
    Bus _bus;

    @Override
    /**
     * basic onCreate for all the activities that extends from CatActivity (all in this project)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dagger config
        ObjectGraph objectGraph = ObjectGraph.create(new VoteCatsModule());
        objectGraph.inject(this);

        // Otto bus registration
        getBus().register(this);

        // Set the UI layout and bind the UI elements for all the activities.
        setContentView(getContentLayoutId());
        ButterKnife.bind(this);
        loadUIElements();
    }

    protected ApplicationComponent getApplicationComponent() {
        return getVoteCatsApplication().getApplicationComponent();
    }

    protected VoteCatsApplication getVoteCatsApplication() {
        return (VoteCatsApplication) getApplication();
    }

    protected Bus getBus() {
        return _bus;
    }

    protected abstract int getContentLayoutId();

    protected abstract void loadUIElements();
}
