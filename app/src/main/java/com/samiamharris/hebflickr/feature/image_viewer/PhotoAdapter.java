package com.samiamharris.hebflickr.feature.image_viewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamIAm on 3/1/18.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private List<Photo> photos = new ArrayList<>();

    private ImageViewerContract.Presenter presenter;

    public PhotoAdapter(ImageViewerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.photo_item, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.bindPhoto(photo);
    }

    @Override
    public int getItemCount() {
        if (photos != null) {
            return photos.size();
        }
        return 0;
    }

    public void setData(List<Photo> photos) {
        this.photos = photos;
    }

    public class PhotoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

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
            presenter.onUserClickThumbnail(photo);
        }
    }

}
