package com.samiamharris.hebflickr;

import com.samiamharris.hebflickr.feature.home.HomeContract;
import com.samiamharris.hebflickr.feature.home.HomePresenter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by SamIAm on 3/2/18.
 */

public class HomePresenterTest {

    private HomeContract.Repository mockRepo;
    private HomePresenter presenter;
    private HomeContract.View mockView;

    @Before
    public void setup() {
        mockRepo = mock(HomeContract.Repository.class);
        mockView = mock(HomeContract.View.class);

        presenter = new HomePresenter();
        presenter.attachView(mockView);
        presenter.setRepo(mockRepo);
    }

    @Test
    public void navigateToImageViewerOnClick(){
        presenter.onUserTapMainButton();
        verify(mockView, times(1)).navigateToImageViewer();
    }
}
