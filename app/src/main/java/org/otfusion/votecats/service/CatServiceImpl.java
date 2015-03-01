package org.otfusion.votecats.service;

import com.squareup.otto.Bus;

import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.providers.CatProvider;
import org.otfusion.votecats.providers.catapi.CatApiAsyncTask;
import org.otfusion.votecats.providers.catapi.CatApiProvider;
import org.otfusion.votecats.providers.catapi.CatApiService;

import javax.inject.Inject;

public class CatServiceImpl implements CatService {

    private Bus _bus;
    private CatApiProvider _catApiProvider;

    @Inject
    public CatServiceImpl(Bus bus, CatApiProvider catApiProvider) {
        _bus = bus;
        _catApiProvider = catApiProvider;
    }

    @Override
    public void execute() {
        CatApiAsyncTask catApiAsyncTask = new CatApiAsyncTask(_bus, _catApiProvider);
        catApiAsyncTask.execute();
    }

}
