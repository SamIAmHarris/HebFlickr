package com.samiamharris.hebflickr;

import com.samiamharris.hebflickr.api.HebServerController;
import com.samiamharris.hebflickr.image_viewer.ImageViewerContract;
import com.samiamharris.hebflickr.image_viewer.ImageViewerPresenter;
import com.samiamharris.hebflickr.model.FlickrPhotosSearchResponse;
import com.samiamharris.hebflickr.model.Photo;
import com.samiamharris.hebflickr.model.PhotoData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
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

    ArgumentCaptor<HebServerController.ResponseSuccessErrorHandler> callbackCaptor
            = ArgumentCaptor.forClass(HebServerController.ResponseSuccessErrorHandler.class);

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
        presenter.onBindView();

        verify(mockRepo, times(1)).fetchPapayaImages(callbackCaptor.capture());

        HebServerController.ResponseSuccessErrorHandler handler = callbackCaptor.getValue();
        handler.onSuccess(getFakeFlickSearchResponse());

        verify(mockView, times(1)).setData(anyList());
    }

    FlickrPhotosSearchResponse getFakeFlickSearchResponse() {
        FlickrPhotosSearchResponse flickrPhotosSearchResponse = new FlickrPhotosSearchResponse();

        PhotoData photoData = new PhotoData();
        Photo photo = new Photo();
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(photo);
        photoData.setPhotos(photos);
        flickrPhotosSearchResponse.setPhotoData(photoData);

        return flickrPhotosSearchResponse;
    }

    @Test
    public void hideProgressBarOnSuccess() {

        presenter.onBindView();

        verify(mockRepo, times(1)).fetchPapayaImages(callbackCaptor.capture());

        HebServerController.ResponseSuccessErrorHandler handler = callbackCaptor.getValue();
        handler.onSuccess(getFakeFlickSearchResponse());

        verify(mockView, times(1)).hideProgressBar();

    }

    @Test
    public void hideProgressBarOnFailure() {
        presenter.onBindView();

        verify(mockRepo, times(1)).fetchPapayaImages(callbackCaptor.capture());

        HebServerController.ResponseSuccessErrorHandler handler = callbackCaptor.getValue();
        handler.onFailure(new Throwable());

        verify(mockView, times(1)).hideProgressBar();
    }

    @Test
    public void showAlertOnFailure() {
        presenter.onBindView();

        verify(mockRepo, times(1)).fetchPapayaImages(callbackCaptor.capture());

        HebServerController.ResponseSuccessErrorHandler handler = callbackCaptor.getValue();
        handler.onFailure(new Throwable());

        verify(mockView, times(1)).showCallFailedAlert();
    }

}
