package org.otfusion.votecats.providers.catapi;

import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.providers.CatProvider;
import org.otfusion.votecats.util.ApplicationUtils;

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
