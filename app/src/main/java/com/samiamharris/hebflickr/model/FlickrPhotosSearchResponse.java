package com.samiamharris.hebflickr.model;

import java.util.List;

/**
 * Created by SamIAm on 3/1/18.
 */

public class FlickrPhotosSearchResponse extends Model {

    public String stat;
    public Photos photos;

    public class Photos {
        public int page;
        public int pages;
        public int perPage;
        public String total;
        public List<Photo> photo;
    }

}
