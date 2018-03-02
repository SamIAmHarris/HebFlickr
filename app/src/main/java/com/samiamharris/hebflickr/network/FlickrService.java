package com.samiamharris.hebflickr.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SamIAm on 3/1/18.
 */

public interface FlickrService {

    @GET("/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
    Observable<FlickrPhotosSearchResponse> listPhotos(@Query("api_key") String apiKey, @Query("tags") String tags);

}
