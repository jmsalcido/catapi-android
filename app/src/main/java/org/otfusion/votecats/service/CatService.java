package org.otfusion.votecats.service;

import android.content.Context;

import org.otfusion.votecats.common.model.Cat;

public interface CatService {

    void getCatFromApi();

    long saveCatToFavorites(Context context, Cat cat);

    boolean isCatInFavorites(Context context, Cat cat);

}
