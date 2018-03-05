package com.samiamharris.hebflickr.feature.image_viewer;

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
        void showProgressBar();
        void hideProgressBar();
        void showCallFailedAlert();
        void showFullscreenImage(Photo photo);
    }

    interface Presenter
            extends BaseContract.Presenter<ImageViewerContract.View, ImageViewerContract.Repository> {
        void onUserClickThumbnail(Photo photo);
    }

    interface Repository extends BaseContract.Repository {
        void fetchPapayaImages(HebServerController.DataHandler onResult);
        void setImageList(List<Photo> photos);
        List<Photo> getImageList();
    }

}
