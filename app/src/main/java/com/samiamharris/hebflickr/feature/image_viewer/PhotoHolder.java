package com.samiamharris.hebflickr.feature.image_viewer;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.model.Photo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamIAm on 3/1/18.
 */

public class PhotoHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    private Photo photo;
    private Context context;

    @BindView(R.id.photo_image)
    ImageView thumbnailImageView;

    PhotoHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    void bindPhoto(Photo photo) {
        this.photo = photo;
        Picasso.with(context).load(photo.buildUrl()).into(thumbnailImageView);
    }

    @Override
    public void onClick(View v) {
        setUpAndShowFullSizeImage();
    }

    private void setUpAndShowFullSizeImage() {

        final Dialog fullSizeDialog = new Dialog(context, android.R.style.ThemeOverlay_Material);

        setupDialogSizeAndStyle(fullSizeDialog);
        setupDialogViews(fullSizeDialog);
    }

    private void setupDialogSizeAndStyle(Dialog fullSizeDialog) {
        Window window = fullSizeDialog.getWindow();
        if (window != null) {
            fullSizeDialog.getWindow().setBackgroundDrawable(context.getDrawable(R.color.transparent_black));
        }
        fullSizeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fullSizeDialog.setCancelable(false);
        fullSizeDialog.setContentView(R.layout.full_size_photo);
        fullSizeDialog.setCancelable(true);
    }

    private void setupDialogViews(Dialog fullSizeDialog) {
        RelativeLayout dialogParent = fullSizeDialog.findViewById(R.id.dialog_parent);
        ImageView fullSizeImage = fullSizeDialog.findViewById(R.id.full_size_image);
        ProgressBar progressBar = fullSizeDialog.findViewById(R.id.large_photo_progress_bar);

        dialogParent.setOnClickListener(view -> fullSizeDialog.dismiss());
        fullSizeImage.setOnClickListener(view -> fullSizeDialog.dismiss());
        fullSizeDialog.show();

        Picasso.with(context).load(photo.buildLargeUrl()).into(fullSizeImage, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
                fullSizeImage.setAlpha(Constant.PHOTO_INITIAL_ALPHA);
                fullSizeImage.animate().alpha(Constant.PHOTO_FINAL_ALPHA).setDuration(Constant.FADE_IN_DURATION_TIME);
            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, R.string.failed_to_load_text, Toast.LENGTH_LONG).show();
                fullSizeDialog.dismiss();
            }
        });
    }

    private class Constant {
        private static final int FADE_IN_DURATION_TIME = 200;
        private static final float PHOTO_INITIAL_ALPHA = 0.0f;
        private static final float PHOTO_FINAL_ALPHA = 1.0f;

    }
}
