package org.otfusion.votecats.providers.catapi;

import android.os.AsyncTask;

import com.squareup.otto.Bus;

import org.otfusion.votecats.common.model.Cat;

public class CatApiAsyncTask extends AsyncTask<Void, Void, Cat> {

    private Bus _bus;
    private CatApiProvider _catApiProvider;

    public CatApiAsyncTask(Bus bus, CatApiProvider catApiProvider) {
        _bus = bus;
        _catApiProvider = catApiProvider;
    }

    @Override
    protected Cat doInBackground(Void... voids) {
        return _catApiProvider.getCatFromProvider();
    }

    @Override
    protected void onPostExecute(Cat cat) {
        super.onPostExecute(cat);
        getBus().post(cat);
    }

    protected Bus getBus() {
        return _bus;
    }
}
