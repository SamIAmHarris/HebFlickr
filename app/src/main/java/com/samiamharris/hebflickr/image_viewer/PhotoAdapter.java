package com.samiamharris.hebflickr.image_viewer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.model.Photo;

import java.util.List;

/**
 * Created by SamIAm on 3/1/18.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder>{

    private List<Photo> photos;

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
        if(photos != null) {
            return photos.size();
        }
        return 0;
    }

    public void setData(List<Photo> photos) {
        this.photos = photos;
    }

}
