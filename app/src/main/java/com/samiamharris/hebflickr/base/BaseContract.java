package com.samiamharris.hebflickr.base;

/**
 * Created by SamIAm on 3/1/18.
 */

public interface BaseContract {


    interface View {

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

        void onStartWithAttachedView();

    }

    interface Repository {

    }

}
