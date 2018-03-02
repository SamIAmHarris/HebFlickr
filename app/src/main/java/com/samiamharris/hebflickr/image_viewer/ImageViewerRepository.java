package com.samiamharris.hebflickr.image_viewer;

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.base.BaseRepository;
import com.samiamharris.hebflickr.model.Model;
import com.samiamharris.hebflickr.model.Photo;

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

    public void fetchPapayaImages(HebServerController.ResponseSuccessErrorHandling onSuccess) {
        HebServerController.fetchPhotos(new HebServerController.ResponseSuccessErrorHandling() {
            @Override
            public void onSuccess(Model model) {
                onSuccess.onSuccess(model);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }

}
