package com.samiamharris.hebflickr.feature.image_viewer;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.base.BaseActivity;
import com.samiamharris.hebflickr.model.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamIAm on 3/1/18.
 */

public class ImageViewerActivity extends BaseActivity<ImageViewerContract.View, ImageViewerContract.Repository,
        ImageViewerContract.Presenter>
        implements ImageViewerContract.View {

    @BindView(R.id.photo_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.photo_recyclerview)
    RecyclerView photoRecyclerView;

    PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);

        photoAdapter = new PhotoAdapter();

        GridLayoutManager gridLayoutManager;

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager = new GridLayoutManager(
                    this, Constant.HORIZONTAL_ROW, LinearLayoutManager.HORIZONTAL, false);
        } else {
            gridLayoutManager = new GridLayoutManager(
                    this, Constant.VERTICAL_COLUMN, LinearLayoutManager.VERTICAL, false);
        }

        photoRecyclerView.setLayoutManager(gridLayoutManager);
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

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showCallFailedAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(ImageViewerActivity.this).create();
        alertDialog.setTitle(getString(R.string.failed_title));
        alertDialog.setMessage(getString(R.string.could_not_load));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                getString(R.string.ok), (dialogInterface, i) -> dialogInterface.dismiss());
        alertDialog.show();
    }

    private static class Constant {
        private static final int VERTICAL_COLUMN = 3;
        private static final int HORIZONTAL_ROW = 2;
    }
}
