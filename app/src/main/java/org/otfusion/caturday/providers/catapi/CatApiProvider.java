package org.otfusion.caturday.providers.catapi;

import android.util.Log;

import com.squareup.otto.Bus;

import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.events.CatLoadedEvent;
import org.otfusion.caturday.events.LoadErrorEvent;
import org.otfusion.caturday.providers.CatProvider;
import org.otfusion.caturday.util.ApplicationUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatApiProvider implements CatProvider {

    public static final String PROVIDER_NAME = "catapi";
    public static final String ENDPOINT = "http://thecatapi.com";

    private CatApiService catApiService;
    private Bus bus;

    @Inject
    public CatApiProvider(CatApiService catApiService, Bus bus) {
        this.catApiService = catApiService;
        this.bus = bus;
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
        String catName = ApplicationUtils.generateRandomCatName(catApiElement.getId());
        cat.setName(catName);
        cat.setProviderId(catApiElement.getId());
        cat.setProviderName(PROVIDER_NAME);
        return cat;
    }

}
