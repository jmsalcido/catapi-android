package org.otfusion.votecats.providers.catapi;

import retrofit.http.GET;

public interface CatApiService {

    @GET("/api/images/get?format=xml")
    CatApiElement getCatApiElementFromEndPoint();

}
