package com.samiamharris.hebflickr.base;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SamIAm on 3/1/18.
 */

public abstract class BaseActivity<
        V extends BaseContract.View,
        R extends BaseContract.Repository,
        P extends BaseContract.Presenter<V, R>> extends AppCompatActivity implements BaseContract.View {

    protected P presenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseViewModel<V, R, P> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        final boolean shouldCreatePresenter = viewModel.getPresenter() == null;

        if (shouldCreatePresenter) {
            viewModel.setPresenter(lazyInitPresenter());
        }
        presenter = viewModel.getPresenter();
        presenter.attachView((V) this);

        if (presenter.getRepo() == null) {
            // Only set repo if one's needed. Otherwise, use persisted repo.
            presenter.setRepo(lazyInitRepository());
        }
        if (shouldCreatePresenter) {
            presenter.onPresenterCreated();
        }
        if (savedInstanceState != null) {
            presenter.onPresenterRestore();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStartWithAttachedView();
    }

    protected abstract P initPresenter();
    protected abstract R initRepository();

    protected final P lazyInitPresenter() {
        if (presenter == null) {
            return initPresenter();
        }
        return presenter;
    }

    protected final R lazyInitRepository() {
        if (presenter == null || presenter.getRepo() == null) {
            return initRepository();
        }
        return presenter.getRepo();
    }

    public P getPresenter() {
        return presenter;
    }
}
