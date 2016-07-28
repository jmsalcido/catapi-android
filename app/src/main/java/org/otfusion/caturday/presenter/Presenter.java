package org.otfusion.caturday.presenter;

import org.otfusion.caturday.view.MvpView;

public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

}
