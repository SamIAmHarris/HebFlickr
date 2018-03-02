package com.samiamharris.hebflickr.image_viewer;

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

    public PhotoHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    public void bindPhoto(Photo photo) {
        this.photo = photo;
        Picasso.with(context).load(photo.buildUrl()).into(thumbnailImageView);
    }

    @Override
    public void onClick(View v) {
        setUpAndShowFullSizeImage();
    }

    public void setUpAndShowFullSizeImage() {

        final Dialog fullSizeDialog = new Dialog(context, android.R.style.ThemeOverlay_Material);
        Window window = fullSizeDialog.getWindow();
        if (window != null) {
            fullSizeDialog.getWindow().setBackgroundDrawable(context.getDrawable(R.color.transparent_black));
        }
        fullSizeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fullSizeDialog.setCancelable(false);
        fullSizeDialog.setContentView(R.layout.full_size_photo);
        fullSizeDialog.setCancelable(true);

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
                fullSizeImage.setAlpha(0.0f);
                fullSizeImage.animate().alpha(1.0f).setDuration(200);
            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, R.string.failed_to_load_text, Toast.LENGTH_LONG).show();
                fullSizeDialog.dismiss();
            }
        });
    }
}
