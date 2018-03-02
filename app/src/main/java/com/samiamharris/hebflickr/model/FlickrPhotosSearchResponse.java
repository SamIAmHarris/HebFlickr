package com.samiamharris.hebflickr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SamIAm on 3/1/18.
 */

public class FlickrPhotosSearchResponse {

    private String stat;
    @SerializedName("photos")
    private PhotoData photoData;

    public class PhotoData {
        private int page;
        private int pages;
        private int perPage;
        private String total;
        @SerializedName("photo")
        private List<Photo> photos;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhoto(List<Photo> photos) {
            this.photos = photos;
        }
    }

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
