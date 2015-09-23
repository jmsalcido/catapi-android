package org.otfusion.votecats.providers.catapi;

import android.os.AsyncTask;

import com.squareup.otto.Bus;

import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.events.CatLoadedEvent;

public class CatApiAsyncTask extends AsyncTask<Void, Void, CatLoadedEvent> {

    private Bus _bus;
    private CatApiProvider _catApiProvider;

    public CatApiAsyncTask(Bus bus, CatApiProvider catApiProvider) {
        _bus = bus;
        _catApiProvider = catApiProvider;
    }

    @Override
    protected CatLoadedEvent doInBackground(Void... voids) {
        Cat cat = _catApiProvider.getCatFromProvider();
        return new CatLoadedEvent(cat, _bus);
    }

    @Override
    protected void onPostExecute(CatLoadedEvent catLoadedEvent) {
        super.onPostExecute(catLoadedEvent);
        catLoadedEvent.executeEvent("cat-api");
    }

    protected Bus getBus() {
        return _bus;
    }
}
