package com.samiamharris.hebflickr.network;

/**
 * Created by SamIAm on 3/1/18.
 */

public class Photo {

    public String id;
    public String owner;
    public String secret;
    public String server;
    public String farm;
    public String title;

    public int ispublic;
    public int isfriend;
    public int isfamily;

    // Thumbnail
    public String url_t;
    public int height_t;
    public int width_t;

    // Large
    public String url_l;
    public int height_l;
    public int width_l;

    // Caption
    public String url_c;
    public int height_c;
    public int width_c;

    // Original
    public String url_o;
    public int height_o;
    public int width_o;

}
