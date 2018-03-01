package com.samiamharris.hebflickr.home;

/**
 * Created by SamIAm on 3/1/18.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    @Override
    public void setView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void onUserTapMainButton() {
        view.navigateToImageViewer();
    }

}
