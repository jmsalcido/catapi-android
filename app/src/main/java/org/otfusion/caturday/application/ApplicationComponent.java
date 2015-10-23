package org.otfusion.caturday.application;

import android.content.Context;

import com.squareup.otto.Bus;

import org.otfusion.caturday.db.repository.FavoriteCatRepository;
import org.otfusion.caturday.service.images.StorageImageService;
import org.otfusion.caturday.ui.activities.CatActivity;
import org.otfusion.caturday.providers.catapi.CatApiProvider;
import org.otfusion.caturday.providers.catapi.CatApiService;
import org.otfusion.caturday.service.CatService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
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
