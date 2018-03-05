package com.samiamharris.hebflickr.robolectric;

import com.samiamharris.hebflickr.feature.home.HomeActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;

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
}
