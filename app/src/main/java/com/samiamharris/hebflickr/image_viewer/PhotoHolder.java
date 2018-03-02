package com.samiamharris.hebflickr.image_viewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.model.Photo;
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

//        Glide
//            .with(itemView.getContext())
//            .load(photo.buildUrl())
//            .into(thumbnailImageView);

        Picasso.with(context).load(photo.buildUrl()).into(thumbnailImageView);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, photo.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
