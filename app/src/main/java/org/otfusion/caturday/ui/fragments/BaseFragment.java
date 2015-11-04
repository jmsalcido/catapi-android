package org.otfusion.caturday.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.otfusion.caturday.application.ApplicationComponent;
import org.otfusion.caturday.application.VoteCatsApplication;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayoutId(), container, false);
        ButterKnife.bind(this, view);
        loadUIContent();
        return view;
    }

    protected ApplicationComponent getApplicationComponent() {
        return getVoteCatsApplication().getApplicationComponent();
    }

    private VoteCatsApplication getVoteCatsApplication() {
        return (VoteCatsApplication) getActivity().getApplication();
    }

    public abstract int getContentLayoutId();

    public abstract void loadUIContent();

}
