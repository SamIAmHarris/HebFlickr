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

        if(view == null) {
            return;
        }

        fetchImages(new HebServerController.ResponseSuccessErrorHandling() {
            @Override
            public void onSuccess(Model model) {
                view.hideProgressBar();
                FlickrPhotosSearchResponse flickrPhotosSearchResponse = (FlickrPhotosSearchResponse) model;
                view.setData(flickrPhotosSearchResponse.photos.photo);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgressBar();
                view.showCallFailedAlert();
            }
        });
    }

    @Override
    public void fetchImages(HebServerController.ResponseSuccessErrorHandling successErrorHandling) {
        ImageViewerContract.Repository repo = getRepo();
        ImageViewerContract.View view = getView();

        if(repo == null || view == null) {
            return;
        }

        view.showProgessBar();
        repo.fetchPapayaImages(successErrorHandling);
    }
}
