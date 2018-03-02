package com.samiamharris.hebflickr.base;

/**
 * Created by SamIAm on 3/1/18.
 */

public class BaseRepository implements BaseContract.Repository {

    public String getLocalizedString(int id) {
        HebApp app = HebApp.getApp();

        if (app == null) {
            return "";
        }

        return app.getString(id);
    }
}
