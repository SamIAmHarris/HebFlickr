package com.samiamharris.hebflickr.image_viewer;

/**
 * Created by SamIAm on 3/1/18.
 */

import com.samiamharris.hebflickr.base.BasePresenter;
import com.samiamharris.hebflickr.home.HomeContract;

public class ImageViewerPresenter extends
        BasePresenter<ImageViewerContract.View, ImageViewerContract.Repository>
        implements ImageViewerContract.Presenter {

    private HomeContract.View view;
}
