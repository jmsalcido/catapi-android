package org.otfusion.caturday.service;

import org.otfusion.caturday.common.model.Cat;

import java.util.List;

public interface CatService {

    void getCatFromApi();

    long saveCatToFavorites(Cat cat);

    void deleteFromFavorites(Cat cat);

    boolean isCatInFavorites(Cat cat);

    List<Cat> getFavoriteCats();

    String getCatFileName(Cat cat, boolean absolute);

}
