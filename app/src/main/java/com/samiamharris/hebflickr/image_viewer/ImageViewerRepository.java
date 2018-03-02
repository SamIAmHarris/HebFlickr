package com.samiamharris.hebflickr.image_viewer;

import com.samiamharris.hebflickr.base.BaseRepository;
import com.samiamharris.hebflickr.network.FlickrPhotosSearchResponse;
import com.samiamharris.hebflickr.network.HebServerController;
import com.samiamharris.hebflickr.network.Model;
import com.samiamharris.hebflickr.network.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamIAm on 3/1/18.
 */

public class ImageViewerRepository extends BaseRepository implements ImageViewerContract.Repository {

    public List<Photo> imageList = new ArrayList<>();

    public List<Photo> getImages() {
        return imageList;
    }

    public void fetchPapayaImages(OnSuccess onSuccess) {
        HebServerController.fetchPhotos(new HebServerController.ResponseSuccessErrorHandling() {
            @Override
            public void onSuccess(Model model) {
                FlickrPhotosSearchResponse photoResponse = (FlickrPhotosSearchResponse) model;
                imageList = photoResponse.photos.photo;
                onSuccess.onSuccess(imageList);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }

}
