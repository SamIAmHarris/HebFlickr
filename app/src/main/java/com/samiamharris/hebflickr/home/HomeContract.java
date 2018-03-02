package com.samiamharris.hebflickr.home;

import com.samiamharris.hebflickr.base.BaseContract;

/**
 * Created by SamIAm on 3/1/18.
 */

public interface HomeContract {

    interface View extends BaseContract.View {
        void navigateToImageViewer();
    }

    interface Presenter extends BaseContract.Presenter<View, Repository> {
        void onUserTapMainButton();
    }

    interface Repository extends BaseContract.Repository {

    }
}
