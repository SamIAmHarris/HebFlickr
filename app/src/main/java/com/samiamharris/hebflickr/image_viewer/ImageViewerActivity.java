package com.samiamharris.hebflickr.image_viewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.base.BaseActivity;
import com.samiamharris.hebflickr.network.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamIAm on 3/1/18.
 */

public class ImageViewerActivity extends BaseActivity<ImageViewerContract.View, ImageViewerContract.Repository,
        ImageViewerContract.Presenter>
        implements ImageViewerContract.View {

    @BindView(R.id.photo_recyclerview)
    RecyclerView photoRecyclerView;

    PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);

        photoAdapter = new PhotoAdapter();
        photoRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        photoRecyclerView.setAdapter(photoAdapter);
    }

    @Override
    protected ImageViewerContract.Presenter initPresenter() {
        return new ImageViewerPresenter();
    }

    @Override
    protected ImageViewerContract.Repository initRepository() {
        return new ImageViewerRepository();
    }

    @Override
    public void setData(List<Photo> photos) {
        photoAdapter.setData(photos);
        photoAdapter.notifyDataSetChanged();
    }
}
