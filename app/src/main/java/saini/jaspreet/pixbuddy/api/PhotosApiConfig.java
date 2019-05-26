package saini.jaspreet.pixbuddy.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import saini.jaspreet.pixbuddy.models.SinglePhoto;

public interface PhotosApiConfig {
    @GET("/api/")
    Call<SinglePhoto> getAllPhotos(@Query("key") String key, @Query("q") String query, @Query("page") int page, @Query("per_page") int perPage);
}
