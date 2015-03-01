package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.otfusion.votecats.application.VoteCatsModule;

import dagger.ObjectGraph;

public abstract class CatActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectGraph objectGraph = ObjectGraph.create(new VoteCatsModule());
        objectGraph.inject(this);
    }
}
