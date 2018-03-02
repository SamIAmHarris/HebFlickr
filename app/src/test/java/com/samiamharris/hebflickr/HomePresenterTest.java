package com.samiamharris.hebflickr;

import com.samiamharris.hebflickr.home.HomeContract;
import com.samiamharris.hebflickr.home.HomePresenter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by SamIAm on 3/2/18.
 */

public class HomePresenterTest {

    HomeContract.Repository mockHomeRepo;
    HomePresenter presenter;
    HomeContract.View mockView;

    @Before
    public void setup() {
        mockHomeRepo = mock(HomeContract.Repository.class);
        mockView = mock(HomeContract.View.class);

        presenter = new HomePresenter();
        presenter.attachView(mockView);
        presenter.setRepo(mockHomeRepo);
    }

    @Test
    public void navigateToImageViewerOnClick(){
        presenter.onUserTapMainButton();
        verify(mockView, times(1)).navigateToImageViewer();
    }
}
