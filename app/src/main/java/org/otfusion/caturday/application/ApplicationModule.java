package org.otfusion.caturday.application;

import android.content.Context;

import com.squareup.otto.Bus;

import org.otfusion.caturday.db.repository.FavoriteCatRepository;
import org.otfusion.caturday.providers.catapi.CatApiProvider;
import org.otfusion.caturday.providers.catapi.CatApiService;
import org.otfusion.caturday.service.CatService;
import org.otfusion.caturday.service.CatServiceImpl;
import org.otfusion.caturday.service.images.StorageImageService;
import org.otfusion.caturday.service.images.picasso.StorageImagePicassoServiceImpl;

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
    public CatService provideCatService(Bus bus, CatApiProvider catApiProvider,
                                        FavoriteCatRepository favoriteCatRepository, StorageImageService storageImageService) {
        return new CatServiceImpl(bus, catApiProvider, favoriteCatRepository, storageImageService);
    }

    @Provides
    @Singleton
    public StorageImageService provideStorageImageService(Context context) {
        return new StorageImagePicassoServiceImpl(context);
    }

    @Provides
    @Singleton
    public CatApiProvider provideCatApiProvider(CatApiService catApiService) {
        return new CatApiProvider(catApiService);
    }

    @Provides
    @Singleton
    public FavoriteCatRepository provideFavoriteCatRepository(Context context) {
        return new FavoriteCatRepository(context);
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
