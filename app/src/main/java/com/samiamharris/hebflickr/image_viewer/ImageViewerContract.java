package com.samiamharris.hebflickr.image_viewer;

import com.samiamharris.hebflickr.base.BaseContract;

/**
 * Created by SamIAm on 3/1/18.
 */

public interface ImageViewerContract {

    interface View extends BaseContract.View {}

    interface Presenter extends BaseContract.Presenter<ImageViewerContract.View, ImageViewerContract.Repository> {}

    interface Repository extends BaseContract.Repository {}

}
