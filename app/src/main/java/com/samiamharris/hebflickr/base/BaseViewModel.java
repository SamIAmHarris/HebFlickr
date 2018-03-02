package com.samiamharris.hebflickr.base;

import android.arch.lifecycle.ViewModel;

/**
 * Created by SamIAm on 3/1/18.
 */

public final class BaseViewModel<V extends BaseContract.View, R extends BaseContract.Repository, P extends BaseContract.Presenter<V, R>>
        extends ViewModel {

    private P presenter;

    void setPresenter(P presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    P getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.onPresenterDestroy();
        presenter = null;
    }
}
