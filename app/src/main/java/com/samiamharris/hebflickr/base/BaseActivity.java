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
        buildFeature(savedInstanceState, viewModel, new BaseContract.FeatureBuilder<V, R, P>() {
            @Override
            public void setPresenter(P newPresenter) {
                presenter = newPresenter;
            }
            @Override
            public P newPresenter() {
                return initPresenter();
            }
            @Override
            public R newRepository() {
                return initRepository();
            }
        });
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
}
