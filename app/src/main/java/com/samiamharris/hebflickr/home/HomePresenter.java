package com.samiamharris.hebflickr.home;

import com.samiamharris.hebflickr.base.BasePresenter;

/**
 * Created by SamIAm on 3/1/18.
 */

public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Repository> implements HomeContract.Presenter {

    @Override
    public void onUserTapMainButton() {
        HomeContract.View view = getView();

        if(view == null) {
            return;
        }

        view.navigateToImageViewer();
    }

}
