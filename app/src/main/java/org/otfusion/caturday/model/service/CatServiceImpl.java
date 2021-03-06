package org.otfusion.caturday.model.service;

import android.support.annotation.NonNull;

import org.otfusion.caturday.common.domain.Cat;
import org.otfusion.caturday.model.repository.db.FavoriteCatDbRepository;
import org.otfusion.caturday.model.providers.CatProvider;
import org.otfusion.caturday.model.service.images.StorageImageService;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class CatServiceImpl implements CatService {

    private CatProvider catProvider;
    private final FavoriteCatDbRepository favoriteCatRepository;
    private final StorageImageService storageImageService;
    private final FileService fileService;

    @Inject
    public CatServiceImpl(CatProvider catProvider, FavoriteCatDbRepository
            favoriteCatRepository, StorageImageService storageImageService, FileService fileService) {
        this.catProvider = catProvider;
        this.favoriteCatRepository = favoriteCatRepository;
        this.storageImageService = storageImageService;
        this.fileService = fileService;
    }

    @Override
    public void getCatFromApi() {
        catProvider.loadCatFromProvider();
    }

    @Override
    public long saveCatToFavorites(@NonNull Cat cat) {
        long catId = favoriteCatRepository.saveFavoriteCat(cat);
        storageImageService.saveImageIntoStorage(cat);
        return catId;
    }

    @Override
    public void deleteFromFavorites(Cat cat) {
        favoriteCatRepository.deleteFromFavorites(cat);
        storageImageService.deleteImageFromStorage(cat);
    }

    @Override
    public boolean isCatInFavorites(@NonNull Cat cat) {
        Map<String, Cat> favoriteCatsMap = favoriteCatRepository.getFavoriteCatsMap();
        return favoriteCatsMap.containsKey(cat.getProviderId());
    }

    @Override
    public List<Cat> getFavoriteCats() {
        return favoriteCatRepository.getFavoriteCats();
    }

    @Override
    public String getCatFileName(Cat cat, boolean absolute) {
        File file = fileService.getFile(cat);
        if(!fileService.doesFileExist(file)) {
            return "";
        }
        return absolute ? file.getAbsolutePath() : file.getPath();
    }
}
