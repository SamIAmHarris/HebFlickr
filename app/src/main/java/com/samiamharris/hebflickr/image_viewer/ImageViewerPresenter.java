package com.samiamharris.hebflickr.image_viewer;

/**
 * Created by SamIAm on 3/1/18.
 */

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.base.BasePresenter;
import com.samiamharris.hebflickr.model.FlickrPhotosSearchResponse;
import com.samiamharris.hebflickr.base.BaseModel;
import com.samiamharris.hebflickr.model.Photo;

import java.util.List;


public class ImageViewerPresenter extends
        BasePresenter<ImageViewerContract.View, ImageViewerContract.Repository>
        implements ImageViewerContract.Presenter {


    @Override
    public void onBindView() {
        super.onBindView();
        ImageViewerContract.View view = getView();
        ImageViewerContract.Repository repo = getRepo();

        if(view == null || repo == null) {
            return;
        }

        if(repo.getImageList().isEmpty()) {

            view.showProgessBar();

            repo.fetchPapayaImages(new HebServerController.ResponseSuccessErrorHandler() {
                @Override
                public void onSuccess(BaseModel model) {
                    view.hideProgressBar();

                    FlickrPhotosSearchResponse searchResponse = (FlickrPhotosSearchResponse) model;
                    List<Photo> photoList = searchResponse.getPhotoData().getPhotos();
                    repo.setImageList(photoList);
                    view.setData(photoList);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    view.hideProgressBar();
                    view.showCallFailedAlert();
                }
            });
        } else {
            view.hideProgressBar();

            view.setData(repo.getImageList());
        }
    }
}
