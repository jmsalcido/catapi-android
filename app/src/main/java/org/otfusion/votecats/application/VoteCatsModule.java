package org.otfusion.votecats.application;

import android.content.Context;

import com.mobprofs.retrofit.converters.SimpleXmlConverter;
import com.squareup.otto.Bus;

import org.otfusion.votecats.db.repository.FavoriteCatRepository;
import org.otfusion.votecats.providers.catapi.CatApiProvider;
import org.otfusion.votecats.providers.catapi.CatApiService;
import org.otfusion.votecats.service.CatService;
import org.otfusion.votecats.service.CatServiceImpl;
import org.otfusion.votecats.service.images.SaveImageService;
import org.otfusion.votecats.service.images.picasso.SaveImagePicassoServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module
public class VoteCatsModule {

    private final VoteCatsApplication _application;

    public VoteCatsModule(VoteCatsApplication application) {
        _application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return _application;
    }

    @Provides
    @Singleton
    public CatService provideCatService(Bus bus, CatApiProvider catApiProvider,
        FavoriteCatRepository favoriteCatRepository, SaveImageService saveImageService) {
        return new CatServiceImpl(bus, catApiProvider, favoriteCatRepository, saveImageService);
    }

    @Provides
    @Singleton
    public SaveImageService provideSaveImageService() {
        return new SaveImagePicassoServiceImpl();
    }

    @Provides
    @Singleton
    public CatApiProvider provideCatApiProvider(CatApiService catApiService) {
        return new CatApiProvider(catApiService);
    }

    @Provides
    @Singleton
    public FavoriteCatRepository provideFavoriteCatRepository() {
        return new FavoriteCatRepository();
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
