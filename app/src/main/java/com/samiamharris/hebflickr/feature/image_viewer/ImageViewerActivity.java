package com.samiamharris.hebflickr.feature.image_viewer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.base.BaseActivity;
import com.samiamharris.hebflickr.model.Photo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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

    Dialog fullSizeDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);

        photoAdapter = new PhotoAdapter(lazyInitPresenter());

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
    protected void onDestroy() {
        super.onDestroy();
        if(fullSizeDialog != null) {
            fullSizeDialog.dismiss();
        }
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

    @Override
    public void showFullscreenImage(Photo photo) {
        fullSizeDialog = new Dialog(ImageViewerActivity.this, android.R.style.ThemeOverlay_Material);

        setupDialogSizeAndStyle(fullSizeDialog);
        setupDialogViews(fullSizeDialog, photo);
    }

    private void setupDialogSizeAndStyle(Dialog fullSizeDialog) {
        Window window = fullSizeDialog.getWindow();
        if (window != null) {
            fullSizeDialog.getWindow().setBackgroundDrawable(getDrawable(R.color.transparent_black));
        }
        fullSizeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fullSizeDialog.setCancelable(false);
        fullSizeDialog.setContentView(R.layout.full_size_photo);
        fullSizeDialog.setCancelable(true);
    }

    private void setupDialogViews(Dialog fullSizeDialog, Photo photo) {
        RelativeLayout dialogParent = fullSizeDialog.findViewById(R.id.dialog_parent);
        ImageView fullSizeImage = fullSizeDialog.findViewById(R.id.full_size_image);
        ProgressBar progressBar = fullSizeDialog.findViewById(R.id.large_photo_progress_bar);

        dialogParent.setOnClickListener(view -> fullSizeDialog.dismiss());
        fullSizeImage.setOnClickListener(view -> fullSizeDialog.dismiss());
        fullSizeDialog.show();

        Picasso.with(ImageViewerActivity.this).load(photo.buildLargeUrl()).into(fullSizeImage, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
                fullSizeImage.setAlpha(Constant.PHOTO_INITIAL_ALPHA);
                fullSizeImage.animate().alpha(Constant.PHOTO_FINAL_ALPHA).setDuration(Constant.FADE_IN_DURATION_TIME);
            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ImageViewerActivity.this, R.string.failed_to_load_text, Toast.LENGTH_LONG).show();
                fullSizeDialog.dismiss();
            }
        });
    }


    private static class Constant {
        private static final int VERTICAL_COLUMN = 3;
        private static final int HORIZONTAL_ROW = 2;

        private static final int FADE_IN_DURATION_TIME = 200;
        private static final float PHOTO_INITIAL_ALPHA = 0.0f;
        private static final float PHOTO_FINAL_ALPHA = 1.0f;
    }
}
