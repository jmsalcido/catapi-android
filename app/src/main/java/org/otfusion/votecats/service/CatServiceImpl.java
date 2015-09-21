package org.otfusion.votecats.service;

import android.support.annotation.NonNull;

import com.squareup.otto.Bus;

import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.db.repository.FavoriteCatRepository;
import org.otfusion.votecats.providers.catapi.CatApiAsyncTask;
import org.otfusion.votecats.providers.catapi.CatApiProvider;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class CatServiceImpl implements CatService {

    private Bus _bus;
    private CatApiProvider _catApiProvider;
    private FavoriteCatRepository _favoriteCatRepository;

    @Inject
    public CatServiceImpl(Bus bus, CatApiProvider catApiProvider, FavoriteCatRepository
            favoriteCatRepository) {
        _bus = bus;
        _catApiProvider = catApiProvider;
        _favoriteCatRepository = favoriteCatRepository;
    }

    @Override
    public void getCatFromApi() {
        CatApiAsyncTask catApiAsyncTask = new CatApiAsyncTask(_bus, _catApiProvider);
        catApiAsyncTask.execute();
    }

    @Override
    public long saveCatToFavorites(@NonNull Cat cat) {
        return _favoriteCatRepository.saveFavoriteCat(cat);
    }

    @Override
    public boolean isCatInFavorites(@NonNull Cat cat) {
        Map<String, Cat> favoriteCatsMap = _favoriteCatRepository.getFavoriteCatsMap();
        return favoriteCatsMap.containsKey(cat.getId());
    }

    @Override
    public List<Cat> getFavoriteCats() {
        return _favoriteCatRepository.getFavoriteCats();
    }
}
