package com.samiamharris.hebflickr.home;

/**
 * Created by SamIAm on 3/1/18.
 */

abstract class HomeContract {

    interface View {
        void navigateToImageViewer();
    }

    interface Presenter {
        void setView(HomeContract.View view);
        void onUserTapMainButton();
    }

    interface Repository {

    }
}
