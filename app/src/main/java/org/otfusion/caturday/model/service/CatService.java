package org.otfusion.caturday.model.service;

import org.otfusion.caturday.common.domain.Cat;

import java.util.List;

public interface CatService {

    void getCatFromApi();

    long saveCatToFavorites(Cat cat);

    void deleteFromFavorites(Cat cat);

    boolean isCatInFavorites(Cat cat);

    List<Cat> getFavoriteCats();

    String getCatFileName(Cat cat, boolean absolute);

}
