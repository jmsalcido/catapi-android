package org.otfusion.votecats.application;

import android.app.Application;

import dagger.ObjectGraph;

public class CatApplication extends Application {

    private ObjectGraph _objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        _objectGraph = ObjectGraph.create(new CatApplicationModule());
    }

    public ObjectGraph getObjectGraph() {
        return _objectGraph;
    }
}
