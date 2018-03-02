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
    }

    interface Presenter extends BaseContract.Presenter<ImageViewerContract.View, ImageViewerContract.Repository> {}

    interface Repository extends BaseContract.Repository {
        List<Photo> getImages();
        void fetchPapayaImages(HebServerController.ResponseSuccessErrorHandling onResult);
    }

}
