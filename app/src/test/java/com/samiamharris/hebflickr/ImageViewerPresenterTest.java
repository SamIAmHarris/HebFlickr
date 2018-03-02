package com.samiamharris.hebflickr;

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.image_viewer.ImageViewerContract;
import com.samiamharris.hebflickr.image_viewer.ImageViewerPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by SamIAm on 3/2/18.
 */

public class ImageViewerPresenterTest {

    ImageViewerContract.Repository mockRepo;
    ImageViewerPresenter presenter;
    ImageViewerContract.View mockView;

    @Captor
    ArgumentCaptor<HebServerController.ResponseSuccessErrorHandler> callbackCaptor;



    @Before
    public void setup() {
        mockRepo = mock(ImageViewerContract.Repository.class);
        mockView = mock(ImageViewerContract.View.class);

        presenter = new ImageViewerPresenter();
        presenter.attachView(mockView);
        presenter.setRepo(mockRepo);
    }

    @Test
    public void fetchImagesOnBind(){
        presenter.onBindView();
        verify(mockRepo, times(1)).fetchPapayaImages(any());
    }

    @Test
    public void showProgressBarOnBind(){
        presenter.onBindView();
        verify(mockView, times(1)).showProgessBar();
    }

    @Test
    public void setDataOnSuccessCall() {
    }

    @Test
    public void hideProgressBarOnSuccess() {

    }

    @Test
    public void hideProgressBarOnFailure() {

    }

    @Test
    public void showAlertOnFailure() {

    }

}
