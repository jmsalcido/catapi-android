package org.otfusion.votecats.service;

import org.otfusion.votecats.common.model.Cat;

import java.util.List;

public interface CatService {

    void getCatFromApi();

    long saveCatToFavorites(Cat cat);

    boolean isCatInFavorites(Cat cat);

    List<Cat> getFavoriteCats();

}
