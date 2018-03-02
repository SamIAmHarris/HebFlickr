package com.samiamharris.hebflickr.feature.image_viewer;

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.base.BaseModel;
import com.samiamharris.hebflickr.base.BaseRepository;
import com.samiamharris.hebflickr.model.FlickrPhotosSearchResponse;
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

    public void fetchPapayaImages(HebServerController.DataHandler handler) {
        HebServerController.fetchPhotos(new HebServerController.ResponseSuccessErrorHandler() {
            @Override
            public void onSuccess(BaseModel model) {
                FlickrPhotosSearchResponse flickrPhotosSearchResponse = (FlickrPhotosSearchResponse) model;
                List<Photo> list = flickrPhotosSearchResponse.getPhotoData().getPhotos();
                setImageList(list);
                handler.onSuccess(list);
            }

            @Override
            public void onFailure(Throwable throwable) {
                handler.onFailure(throwable);
            }
        });
    }

}
