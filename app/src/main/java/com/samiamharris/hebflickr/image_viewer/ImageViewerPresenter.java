package com.samiamharris.hebflickr.image_viewer;

/**
 * Created by SamIAm on 3/1/18.
 */

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.base.BasePresenter;
import com.samiamharris.hebflickr.model.FlickrPhotosSearchResponse;
import com.samiamharris.hebflickr.model.Model;


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

        view.showProgessBar();

        repo.fetchPapayaImages(new HebServerController.ResponseSuccessErrorHandler() {
            @Override
            public void onSuccess(Model model) {
                view.hideProgressBar();
                FlickrPhotosSearchResponse searchResponse = (FlickrPhotosSearchResponse) model;
                view.setData(searchResponse.getPhotoData().getPhotos());
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgressBar();
                view.showCallFailedAlert();
            }
        });
    }
}
