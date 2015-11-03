package org.otfusion.caturday.providers.catapi;

import retrofit.http.GET;

public interface CatApiService {

    @GET("/api/images/get?format=xml")
    public CatApiElement getCatApiElementFromEndPoint();

}
