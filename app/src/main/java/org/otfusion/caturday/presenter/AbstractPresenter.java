package org.otfusion.caturday.presenter;

import org.otfusion.caturday.view.MvpView;

public abstract class AbstractPresenter<V extends MvpView> implements Presenter<V> {

    private V mvpView;

    @Override
    public void attachView(V view) {
        mvpView = view;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    public V getMvpView() {
        return mvpView;
    }
}
