package com.samiamharris.hebflickr.robolectric;

import com.samiamharris.hebflickr.feature.home.HomeActivity;
import com.samiamharris.hebflickr.feature.home.HomeContract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by SamIAm on 3/2/18.
 */
@RunWith(RobolectricTestRunner.class)
public class MVPSetupTest {

    @Test
    public void testAllPartsOfFeatureAreNotNull() {
        HomeActivity activity = Robolectric.setupActivity(HomeActivity.class);

        assertNotNull(activity.getPresenter());
        assertNotNull(activity.getPresenter().getRepo());
        assertNotNull(activity.getPresenter().getView());
    }

    @Test
    public void testDetachViewOnDestroy() {
        ActivityController controller = Robolectric.buildActivity(HomeActivity.class).create().start();
        HomeActivity activity = (HomeActivity) controller.get();

        HomeContract.Presenter presenter = activity.getPresenter();

        assertNotNull(presenter.getView());

        controller.destroy();

        assertNull(presenter.getView());
    }


}
