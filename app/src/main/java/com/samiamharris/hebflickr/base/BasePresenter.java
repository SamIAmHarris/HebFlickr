package com.samiamharris.hebflickr.base;

import android.support.annotation.CallSuper;

/**
 * Created by SamIAm on 3/1/18.
 */

public abstract class BasePresenter<V extends BaseContract.View, R extends BaseContract.Repository>
        implements BaseContract.Presenter<V, R> {

    private V view;
    private R repo;

    private Boolean shouldBindView = true;

    @Override
    final public V getView() {
        return view;
    }

    @Override
    final public R getRepo() {
        return repo;
    }

    @Override
    final public void setRepo(R repo) {
        this.repo = repo;
    }

    @Override
    final public void attachView(V view) {
        this.view = view;
    }

    @Override
    final public void detachView() {
        view = null;
    }

    @Override
    public void onPresenterCreated() {
        // Subclasses can override.
    }

    @Override
    final public void onPresenterRestore() {
        this.shouldBindView = true;
    }

    @CallSuper
    @Override
    public void onPresenterDestroy() {
        //Subclasses can override.
    }

    public void onStartWithAttachedView() {
        // Subclasses should override.
        if (shouldBindView) {
            onBindView();
        }
    }

    public void onStopWithAttachedView() {

    }

    public void onBindView() {
        shouldBindView = false;
    }

}
