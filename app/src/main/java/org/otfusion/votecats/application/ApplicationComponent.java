package org.otfusion.votecats.application;

import android.content.Context;

import com.squareup.otto.Bus;

import org.otfusion.votecats.providers.catapi.CatApiProvider;
import org.otfusion.votecats.providers.catapi.CatApiService;
import org.otfusion.votecats.service.CatServiceImpl;
import org.otfusion.votecats.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = VoteCatsModule.class)
public interface ApplicationComponent {
    void inject(MainActivity catActivity);

    Context context();
    CatServiceImpl catService();
    CatApiProvider catApiProvider();
    CatApiService catApiService();
    Bus bus();
}
