package com.samiamharris.hebflickr.base;

import android.os.Bundle;

/**
 * Created by SamIAm on 3/1/18.
 */

public interface BaseContract {

    interface FeatureBuilder<V extends View, R extends Repository, P extends Presenter<V, R>> {

        void setPresenter(P presenter);

        P newPresenter();
        R newRepository();

    }

    @SuppressWarnings("unchecked")
    interface View {

        default <V extends View, R extends Repository, P extends Presenter<V, R>> void buildFeature(
                Bundle savedInstanceState, BaseViewModel<V, R, P> viewModel, FeatureBuilder<V, R, P> builder) {

            final boolean shouldCreatePresenter = viewModel.getPresenter() == null;

            if (shouldCreatePresenter) {
                viewModel.setPresenter(builder.newPresenter());
            }
            P presenter = viewModel.getPresenter();
            presenter.attachView((V) this);
            builder.setPresenter(presenter);
            if (presenter.getRepo() == null) {
                // Only set repo if one's needed. Otherwise, use persisted repo.
                presenter.setRepo(builder.newRepository());
            }
            if (shouldCreatePresenter) {
                presenter.onPresenterCreated();
            }
            if (savedInstanceState != null) {
                presenter.onPresenterRestore();
            }
        }

    }

    interface Presenter<V extends BaseContract.View, R extends BaseContract.Repository> {

        void attachView(V view);

        void setRepo(R repo);

        void detachView();

        V getView();

        R getRepo();

        void onPresenterCreated();

        void onPresenterRestore();

        void onPresenterDestroy();

    }

    interface Repository {
    }



}
