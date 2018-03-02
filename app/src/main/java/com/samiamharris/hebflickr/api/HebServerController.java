package com.samiamharris.hebflickr.api;

import android.support.annotation.NonNull;

import com.samiamharris.hebflickr.BuildConfig;
import com.samiamharris.hebflickr.model.FlickrPhotosSearchResponse;
import com.samiamharris.hebflickr.model.Model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SamIAm on 3/1/18.
 */

public abstract class HebServerController {

    // Private Properties

    private final static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private static Client client = retrofit.create(Client.class);

    public interface ResponseSuccessErrorHandler {

        void onSuccess(Model model);
        void onFailure(Throwable throwable);

    }

    public static void fetchPhotos(ResponseSuccessErrorHandler handler) {
        client.listPhotos(BuildConfig.FLICK_API_KEY, "papaya", 60).enqueue(new Callback<FlickrPhotosSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<FlickrPhotosSearchResponse> call, @NonNull Response<FlickrPhotosSearchResponse> response) {
                handler.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<FlickrPhotosSearchResponse> call, @NonNull Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    /*
    * Just in here to show an rx option
    */
    public static void rxFetchPhotos(ResponseSuccessErrorHandler handler) {
        client.rxListPhotos(BuildConfig.FLICK_API_KEY, "papaya", 60)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<FlickrPhotosSearchResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(FlickrPhotosSearchResponse flickrPhotosSearchResponse) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }


    private interface Client {

        @GET("/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
        Call<FlickrPhotosSearchResponse> listPhotos(
                @Query("api_key") String apiKey,
                @Query("tags") String tags,
                @Query("per_page") int perPage);



        /*
        * Just in here to show an rx option
        */
        @GET("/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
        io.reactivex.Observable<FlickrPhotosSearchResponse> rxListPhotos(
                @Query("api_key") String apiKey,
                @Query("tags") String tags,
                @Query("per_page") int perPage);
    }

}
