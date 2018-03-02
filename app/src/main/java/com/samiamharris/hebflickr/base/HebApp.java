package com.samiamharris.hebflickr.base;

import android.app.Application;

/**
 * Created by SamIAm on 3/1/18.
 */

public class HebApp extends Application {

    // Statically Private Properties

    private static HebApp app;

    // Exposed Methods

    public HebApp() {
        app = this;
    }

    // Statically Exposed Methods

    public static HebApp getApp() {
        return app;
    }

}
