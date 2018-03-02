package com.samiamharris.hebflickr.image_viewer;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by SamIAm on 3/1/18.
 */

public class ImageViewerActivity extends BaseActivity<ImageViewerContract.View, ImageViewerContract.Repository,
        ImageViewerContract.Presenter>
        implements ImageViewerContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);
    }

    @Override
    protected ImageViewerContract.Presenter initPresenter() {
        return new ImageViewerPresenter();
    }

    @Override
    protected ImageViewerContract.Repository initRepository() {
        return new ImageViewerRepository();
    }
}
