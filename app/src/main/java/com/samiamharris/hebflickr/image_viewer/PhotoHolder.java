package com.samiamharris.hebflickr.image_viewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.network.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamIAm on 3/1/18.
 */

public class PhotoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private Photo photo;
        Context context;

        @BindView(R.id.photo_text)
        TextView titleTextView;

        public PhotoHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void bindPhoto(Photo photo) {
            this.photo = photo;
            titleTextView.setText(photo.id);

//            Glide
//                    .with(itemView.getContext())
//                    .load(movie.getPosters().getThumbnail())
//                    .centerCrop()
//                    .placeholder(R.mipmap.ic_launcher)
//                    .crossFade()
//                    .into(thumbnailImageView);
        }

        @Override
        public void onClick(View v) {
        }
}
