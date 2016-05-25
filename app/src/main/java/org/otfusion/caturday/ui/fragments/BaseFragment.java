package org.otfusion.caturday.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.squareup.otto.Bus;

import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.service.CatService;
import org.otfusion.caturday.ui.activities.CatActivity;
import org.otfusion.caturday.util.ApplicationUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Inject
    Bus bus;

    @Inject
    CatService catService;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        VoteCatsApplication voteCatsApplication = ApplicationUtils.getApplication(activity);
        voteCatsApplication.getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayoutId(), container, false);
        ButterKnife.bind(this, view);
        getBus().register(this);
        loadUIContent();
        ActionBar supportActionBar = getCatActivity().getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getTitle());
        }
        return view;
    }

    protected CatActivity getCatActivity() {
        return (CatActivity) getActivity();
    }

    public String getTitle() {
        return getActivity().getResources().getString(getTitleId());
    }

    protected AdapterView.AdapterContextMenuInfo getAdapterContextMenuInfo(
            ContextMenu.ContextMenuInfo menuInfo) {
        return (AdapterView.AdapterContextMenuInfo) menuInfo;
    }

    protected Bus getBus() {
        return bus;
    }

    protected CatService getCatService() {
        return catService;
    }

    public abstract int getContentLayoutId();

    public abstract void loadUIContent();

    public abstract int getTitleId();

}
