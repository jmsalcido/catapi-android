package org.otfusion.caturday.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.widget.AdapterView;

import com.squareup.otto.Bus;

import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.service.CatService;
import org.otfusion.caturday.util.ApplicationUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class CatActivity extends AppCompatActivity {

    @Inject
    Bus bus;

    @Inject
    CatService catService;

    @Override
    /**
     * basic onCreate for all the activities that extends from CatActivity (all in this project)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inject dependencies
        VoteCatsApplication voteCatsApplication = ApplicationUtils.getApplication(this);
        voteCatsApplication.getApplicationComponent().inject(this);

        // Otto bus registration
        bus.register(this);

        // Set the UI layout and bind the UI elements for all the activities.
        setContentView(getContentLayoutId());
        ButterKnife.bind(this);
        loadUIContent();
    }

    protected Bus getBus() {
        return bus;
    }

    protected CatService getCatService() {
        return catService;
    }

    protected abstract int getContentLayoutId();

    protected abstract void loadUIContent();
}
