package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.otfusion.votecats.application.ApplicationComponent;
import org.otfusion.votecats.application.VoteCatsApplication;

public abstract class CatActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return getVoteCatsApplication().getApplicationComponent();
    }

    protected VoteCatsApplication getVoteCatsApplication() {
        return (VoteCatsApplication) getApplication();
    }
}
