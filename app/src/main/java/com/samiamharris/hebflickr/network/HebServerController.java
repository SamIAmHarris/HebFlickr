package com.samiamharris.hebflickr.network;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private static Client client = retrofit.create(Client.class);

    public interface ResponseSuccessErrorHandling {

        void onSuccess(Model model);
        void onFailure(Throwable throwable);

    }

    public static void fetchPhotos(ResponseSuccessErrorHandling handler) {
        client.listPhotos("4b8ac29261e4c2f55486d76cc7c75bcd", "papaya")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlickrPhotosSearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("boogie", "onSubscribe");
                    }

                    @Override
                    public void onNext(FlickrPhotosSearchResponse flickrPhotosSearchResponse) {
                        Log.i("boogie", "onNext");
                        handler.onSuccess(flickrPhotosSearchResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("boogie", "onError");
                        handler.onFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("boogie", "onComplete");
                    }
                });
    }


    private interface Client {

        @GET("/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
        Observable<FlickrPhotosSearchResponse> listPhotos(@Query("api_key") String apiKey, @Query("tags") String tags);

    }

}
