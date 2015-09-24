package org.otfusion.votecats.application;

import android.content.Context;

import com.squareup.otto.Bus;

import org.otfusion.votecats.db.repository.FavoriteCatRepository;
import org.otfusion.votecats.providers.catapi.CatApiProvider;
import org.otfusion.votecats.providers.catapi.CatApiService;
import org.otfusion.votecats.service.CatService;
import org.otfusion.votecats.service.images.StorageImageService;
import org.otfusion.votecats.ui.activities.CatActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = VoteCatsModule.class)
public interface ApplicationComponent {
    void inject(CatActivity catActivity);

    Context context();
    CatService catService();
    CatApiProvider catApiProvider();
    CatApiService catApiService();
    FavoriteCatRepository favoriteCatRepository();
    StorageImageService storageImageService();
    Bus bus();
}
