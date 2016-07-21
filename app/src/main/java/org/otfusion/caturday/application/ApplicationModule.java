package org.otfusion.caturday.application;

import android.content.Context;

import com.squareup.otto.Bus;

import org.otfusion.caturday.db.repository.FavoriteCatRepository;
import org.otfusion.caturday.providers.catapi.CatApiProvider;
import org.otfusion.caturday.providers.catapi.CatApiService;
import org.otfusion.caturday.service.AndroidFileService;
import org.otfusion.caturday.service.CatService;
import org.otfusion.caturday.service.CatServiceImpl;
import org.otfusion.caturday.service.FileService;
import org.otfusion.caturday.service.images.StorageImageService;
import org.otfusion.caturday.service.images.picasso.StorageImagePicassoServiceImpl;
import org.otfusion.caturday.util.CatNameGenerator;
import org.otfusion.caturday.util.RandomCatNameGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module
public class ApplicationModule {

    private final VoteCatsApplication application;

    public ApplicationModule(VoteCatsApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public CatService provideCatService(CatApiProvider catApiProvider,
                                        FavoriteCatRepository favoriteCatRepository,
                                        StorageImageService storageImageService,
                                        FileService fileService) {
        return new CatServiceImpl(catApiProvider, favoriteCatRepository, storageImageService, fileService);
    }

    @Provides
    @Singleton
    public StorageImageService provideStorageImageService(FileService fileService) {
        return new StorageImagePicassoServiceImpl(application, fileService);
    }

    @Provides
    @Singleton
    public CatApiProvider provideCatApiProvider(CatApiService catApiService, Bus bus, CatNameGenerator catNameGenerator) {
        return new CatApiProvider(catApiService, bus, catNameGenerator);
    }

    @Provides
    @Singleton
    public FileService provideFileService() {
        return new AndroidFileService(application);
    }

    @Provides
    @Singleton
    public CatNameGenerator provideCatNameGenerator() {
        return new RandomCatNameGenerator();
    }

    @Provides
    @Singleton
    public FavoriteCatRepository provideFavoriteCatRepository() {
        return new FavoriteCatRepository(application);
    }

    @Provides
    @Singleton
    public CatApiService provideCatApiService() {
        Retrofit restAdapter = new Retrofit.Builder().baseUrl(CatApiProvider.ENDPOINT)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return restAdapter.create(CatApiService.class);
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus("catApi");
    }

}
