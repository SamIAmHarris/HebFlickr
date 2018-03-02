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
        ImageViewerContract.Repository repo = getRepo();
        ImageViewerContract.View view = getView();

        if(repo == null || view == null) {
            return;
        }

        repo.fetchPapayaImages(new HebServerController.ResponseSuccessErrorHandling() {
            @Override
            public void onSuccess(Model model) {
                FlickrPhotosSearchResponse flickrPhotosSearchResponse = (FlickrPhotosSearchResponse) model;
                view.setData(flickrPhotosSearchResponse.photos.photo);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
