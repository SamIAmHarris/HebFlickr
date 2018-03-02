package com.samiamharris.hebflickr;

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.feature.image_viewer.ImageViewerContract;
import com.samiamharris.hebflickr.feature.image_viewer.ImageViewerPresenter;
import com.samiamharris.hebflickr.model.Photo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by SamIAm on 3/2/18.
 */

public class ImageViewerPresenterTest {

    private ImageViewerContract.Repository mockRepo;
    private ImageViewerPresenter presenter;
    private ImageViewerContract.View mockView;

    private ArgumentCaptor<HebServerController.DataHandler> callbackCaptor
            = ArgumentCaptor.forClass(HebServerController.DataHandler.class);

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
        verify(mockView, times(1)).showProgressBar();
    }

    @Test
    public void setDataOnSuccessCall() {
        presenter.onBindView();

        verify(mockRepo, times(1)).fetchPapayaImages(callbackCaptor.capture());

        HebServerController.DataHandler handler = callbackCaptor.getValue();
        handler.onSuccess(getFakeDataList());

        verify(mockView, times(1)).setData(anyList());
    }

    @Test
    public void hideProgressBarOnSuccess() {
        presenter.onBindView();

        verify(mockRepo, times(1)).fetchPapayaImages(callbackCaptor.capture());

        HebServerController.DataHandler handler = callbackCaptor.getValue();
        handler.onSuccess(getFakeDataList());

        verify(mockView, times(1)).hideProgressBar();

    }

    @Test
    public void hideProgressBarOnFailure() {
        presenter.onBindView();

        verify(mockRepo, times(1)).fetchPapayaImages(callbackCaptor.capture());

        HebServerController.DataHandler handler = callbackCaptor.getValue();
        handler.onFailure(new Throwable());

        verify(mockView, times(1)).hideProgressBar();
    }

    @Test
    public void showAlertOnFailure() {
        presenter.onBindView();

        verify(mockRepo, times(1)).fetchPapayaImages(callbackCaptor.capture());

        HebServerController.DataHandler handler = callbackCaptor.getValue();
        handler.onFailure(new Throwable());

        verify(mockView, times(1)).showCallFailedAlert();
    }

    private List<Photo> getFakeDataList() {
        Photo photo = new Photo();
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(photo);

        return photos;
    }
}
