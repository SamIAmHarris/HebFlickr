package com.samiamharris.hebflickr.image_viewer;

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.base.BaseContract;
import com.samiamharris.hebflickr.model.Photo;

import java.util.List;

/**
 * Created by SamIAm on 3/1/18.
 */

public interface ImageViewerContract {

    interface View extends BaseContract.View {
        void setData(List<Photo> photos);
        void showProgessBar();
        void hideProgressBar();
        void showCallFailedAlert();
    }

    interface Presenter extends BaseContract.Presenter<ImageViewerContract.View, ImageViewerContract.Repository> {
    }

    interface Repository extends BaseContract.Repository {
        void fetchPapayaImages(HebServerController.ResponseSuccessErrorHandler onResult);
        void setImageList(List<Photo> photos);
        List<Photo> getImageList();
    }

}
