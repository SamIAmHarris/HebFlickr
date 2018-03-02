package com.samiamharris.hebflickr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SamIAm on 3/2/18.
 */

public class PhotoData {
    private int page;
    private int pages;
    private int perPage;
    private String total;
    @SerializedName("photo")
    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}
