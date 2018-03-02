package com.samiamharris.hebflickr.image_viewer;

/**
 * Created by SamIAm on 3/1/18.
 */

import com.samiamharris.hebflickr.base.BasePresenter;

public class ImageViewerPresenter extends
        BasePresenter<ImageViewerContract.View, ImageViewerContract.Repository>
        implements ImageViewerContract.Presenter {

    @Override
    public void onBindView() {
        super.onBindView();
        ImageViewerContract.Repository repo = getRepo();

        if(repo == null) {
            return;
        }

        repo.fetchPapayaImages();
    }
}
