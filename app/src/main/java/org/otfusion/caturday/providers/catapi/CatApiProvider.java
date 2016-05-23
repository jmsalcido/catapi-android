package org.otfusion.caturday.providers.catapi;

import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.providers.CatProvider;
import org.otfusion.caturday.util.ApplicationUtils;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class CatApiProvider implements CatProvider {

    public static final String PROVIDER_NAME = "catapi";
    public static final String ENDPOINT = "http://thecatapi.com";

    private CatApiService _catApiService;

    @Inject
    public CatApiProvider(CatApiService catApiService) {
        _catApiService = catApiService;
    }

    @Override
    public Cat getCatFromProvider() {
        Cat cat = new Cat();
        Call<CatApiElement> catApiElementFromEndPoint = _catApiService.getCatApiElementFromEndPoint();
        Response<CatApiElement> execute;
        CatApiElement catApiElement;
        try {
            execute = catApiElementFromEndPoint.execute();
            catApiElement = execute.body();
        } catch (IOException e) {
            catApiElement = new CatApiElement();
        }
        cat.setImageUrl(catApiElement.getUrl());
        String catName = ApplicationUtils.generateRandomCatName(catApiElement.getId());
        cat.setName(catName);
        cat.setProviderId(catApiElement.getId());
        cat.setProviderName(PROVIDER_NAME);
        return cat;
    }

}
