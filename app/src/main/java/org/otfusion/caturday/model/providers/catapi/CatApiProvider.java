package org.otfusion.caturday.model.providers.catapi;

import android.util.Log;

import com.squareup.otto.Bus;

import org.otfusion.caturday.common.domain.Cat;
import org.otfusion.caturday.view.common.events.CatLoadedEvent;
import org.otfusion.caturday.view.common.events.LoadErrorEvent;
import org.otfusion.caturday.model.providers.CatProvider;
import org.otfusion.caturday.model.util.CatNameGenerator;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatApiProvider implements CatProvider {

    public static final String PROVIDER_NAME = "catapi";
    public static final String ENDPOINT = "http://thecatapi.com";

    private final CatApiService catApiService;
    private final Bus bus;
    private final CatNameGenerator catNameGenerator;

    @Inject
    public CatApiProvider(CatApiService catApiService, Bus bus, CatNameGenerator catNameGenerator) {
        this.catApiService = catApiService;
        this.bus = bus;
        this.catNameGenerator = catNameGenerator;
    }

    @Override
    public void loadCatFromProvider() {
        Call<CatApiElement> catApiElementFromEndPoint = catApiService.getCatApiElementFromEndPoint();
        catApiElementFromEndPoint.enqueue(new Callback<CatApiElement>() {
            @Override
            public void onResponse(Call<CatApiElement> call, Response<CatApiElement> response) {
                Cat cat = buildCatFromApi(response.body());
                new CatLoadedEvent(cat, bus).executeEvent(PROVIDER_NAME);
            }

            @Override
            public void onFailure(Call<CatApiElement> call, Throwable t) {
                Log.w(PROVIDER_NAME, t.getMessage());
                new LoadErrorEvent(bus, t.getMessage()).executeEvent("fail-" + PROVIDER_NAME);
            }
        });
    }

    private Cat buildCatFromApi(CatApiElement catApiElement) {
        Cat cat = new Cat();
        cat.setImageUrl(catApiElement.getUrl());
        String catName = catNameGenerator.generateName(catApiElement.getId());
        cat.setName(catName);
        cat.setProviderId(catApiElement.getId());
        cat.setProviderName(PROVIDER_NAME);
        return cat;
    }

}
