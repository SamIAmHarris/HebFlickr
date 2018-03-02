package com.samiamharris.hebflickr.image_viewer;

/**
 * Created by SamIAm on 3/1/18.
 */

import com.samiamharris.hebflickr.base.BasePresenter;
import com.samiamharris.hebflickr.network.Photo;

import java.util.List;

interface OnSuccess {
        void onSuccess(List<Photo> photoList);
    }

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

        repo.fetchPapayaImages(new OnSuccess() {
            @Override
            public void onSuccess(List<Photo> photoList) {
                view.setData(photoList);
            }
        });
    }
}
