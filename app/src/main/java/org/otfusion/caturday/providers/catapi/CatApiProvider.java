package org.otfusion.caturday.providers.catapi;

import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.providers.CatProvider;
import org.otfusion.caturday.util.ApplicationUtils;

import javax.inject.Inject;

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
        CatApiElement catApiElementFromEndPoint = _catApiService.getCatApiElementFromEndPoint();
        cat.setImageUrl(catApiElementFromEndPoint.getUrl());
        String catName = ApplicationUtils.generateRandomCatName(catApiElementFromEndPoint.getId());
        cat.setName(catName);
        cat.setProviderId(catApiElementFromEndPoint.getId());
        cat.setProviderName(PROVIDER_NAME);
        return cat;
    }

}
