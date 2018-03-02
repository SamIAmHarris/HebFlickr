package com.samiamharris.hebflickr.model;

import com.google.gson.annotations.SerializedName;
import com.samiamharris.hebflickr.base.BaseModel;

/**
 * Created by SamIAm on 3/1/18.
 */

public class FlickrPhotosSearchResponse extends BaseModel {

    private String stat;
    @SerializedName("photos")
    private PhotoData photoData;


    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public PhotoData getPhotoData() {
        return photoData;
    }

    public void setPhotoData(PhotoData photoData) {
        this.photoData = photoData;
    }
}
