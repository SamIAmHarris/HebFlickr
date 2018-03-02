package com.samiamharris.hebflickr.image_viewer;

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.base.BaseRepository;
import com.samiamharris.hebflickr.model.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamIAm on 3/1/18.
 */

public class ImageViewerRepository extends BaseRepository implements ImageViewerContract.Repository {

    private List<Photo> imageList = new ArrayList<>();

    public void setImageList(List<Photo> imageList) {
        this.imageList = imageList;
    }

    @Override
    public List<Photo> getImageList() {
        return imageList;
    }

    public void fetchPapayaImages(HebServerController.ResponseSuccessErrorHandler handler) {
        HebServerController.fetchPhotos(handler);
    }

}
