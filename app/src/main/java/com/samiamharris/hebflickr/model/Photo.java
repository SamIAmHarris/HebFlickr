package com.samiamharris.hebflickr.model;

/**
 * Created by SamIAm on 3/1/18.
 */

public class Photo {

    private String id;
    private String owner;
    private String secret;
    private String server;
    private String farm;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public String getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String buildUrl() {
        return String.format(Constant.NORMAL_SIZE_FORMAT, getFarm(), getServer(), getId(), getSecret());
    }

    public String buildLargeUrl() {
        return String.format(Constant.LARGE_SIZE_FORMAT, getFarm(), getServer(), getId(), getSecret());
    }

    private static class Constant {
        private static String NORMAL_SIZE_FORMAT = "https://farm%s.staticflickr.com/%s/%s_%s_q.jpg";
        private static String LARGE_SIZE_FORMAT = "https://farm%s.staticflickr.com/%s/%s_%s_b.jpg";

    }
}
