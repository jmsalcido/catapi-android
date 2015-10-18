package org.otfusion.votecats.providers.catapi;

import android.os.AsyncTask;

import com.squareup.otto.Bus;

import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.events.CatLoadedEvent;

public class CatApiAsyncTask extends AsyncTask<Void, Void, CatLoadedEvent> {

    private Bus bus;
    private CatApiProvider catApiProvider;

    public CatApiAsyncTask(Bus bus, CatApiProvider catApiProvider) {
        this.bus = bus;
        this.catApiProvider = catApiProvider;
    }

    @Override
    protected CatLoadedEvent doInBackground(Void... voids) {
        Cat cat = catApiProvider.getCatFromProvider();
        return new CatLoadedEvent(cat, bus);
    }

    @Override
    protected void onPostExecute(CatLoadedEvent catLoadedEvent) {
        super.onPostExecute(catLoadedEvent);
        catLoadedEvent.executeEvent("cat-api");
    }

    protected Bus getBus() {
        return bus;
    }
}
