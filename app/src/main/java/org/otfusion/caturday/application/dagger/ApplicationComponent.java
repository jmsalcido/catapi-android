package org.otfusion.caturday.application.dagger;

import android.content.Context;

import com.squareup.otto.Bus;

import org.otfusion.caturday.model.repository.db.FavoriteCatDbRepository;
import org.otfusion.caturday.model.providers.catapi.CatApiProvider;
import org.otfusion.caturday.model.providers.catapi.CatApiService;
import org.otfusion.caturday.model.service.CatService;
import org.otfusion.caturday.model.service.images.StorageImageService;
import org.otfusion.caturday.view.common.activity.CatActivity;
import org.otfusion.caturday.view.caturday.activity.CaturdayActivity;
import org.otfusion.caturday.ui.fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(CatActivity catActivity);
    void inject(CaturdayActivity catActivity);
    void inject(BaseFragment fragment);

    Context context();
    CatService catService();
    CatApiProvider catApiProvider();
    CatApiService catApiService();
    FavoriteCatDbRepository favoriteCatRepository();
    StorageImageService storageImageService();
    Bus bus();
}
