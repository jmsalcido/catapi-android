package org.otfusion.votecats.application;

import android.content.Context;

import com.mobprofs.retrofit.converters.SimpleXmlConverter;
import com.squareup.otto.Bus;

import org.otfusion.votecats.db.repository.FavoriteCatRepository;
import org.otfusion.votecats.providers.catapi.CatApiProvider;
import org.otfusion.votecats.providers.catapi.CatApiService;
import org.otfusion.votecats.service.CatService;
import org.otfusion.votecats.service.CatServiceImpl;
import org.otfusion.votecats.service.images.StorageImageService;
import org.otfusion.votecats.service.images.picasso.StorageImagePicassoServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

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
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(CatApiProvider.ENDPOINT)
                .setConverter(new SimpleXmlConverter())
                .build();
        return restAdapter.create(CatApiService.class);
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }

}
