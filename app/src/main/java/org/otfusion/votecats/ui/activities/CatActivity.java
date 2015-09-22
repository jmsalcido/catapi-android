package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.widget.AdapterView;

import com.squareup.otto.Bus;

import org.otfusion.votecats.application.ApplicationComponent;
import org.otfusion.votecats.application.VoteCatsApplication;
import org.otfusion.votecats.service.CatService;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class CatActivity extends AppCompatActivity {

    @Inject
    Bus _bus;

    @Inject
    CatService _catService;

    @Override
    /**
     * basic onCreate for all the activities that extends from CatActivity (all in this project)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inject dependencies
        getApplicationComponent().inject(this);

        // Otto bus registration
        getBus().register(this);

        // Set the UI layout and bind the UI elements for all the activities.
        setContentView(getContentLayoutId());
        ButterKnife.bind(this);
        loadContent();
    }

    protected ApplicationComponent getApplicationComponent() {
        return getVoteCatsApplication().getApplicationComponent();
    }

    protected VoteCatsApplication getVoteCatsApplication() {
        return (VoteCatsApplication) getApplication();
    }

    protected Bus getBus() {
        return _bus;
    }

    protected CatService getCatService() {
        return _catService;
    }

    protected AdapterView.AdapterContextMenuInfo getAdapterContextMenuInfo(
            ContextMenu.ContextMenuInfo menuInfo) {
        return (AdapterView.AdapterContextMenuInfo) menuInfo;
    }

    protected abstract int getContentLayoutId();

    protected abstract void loadContent();
}
