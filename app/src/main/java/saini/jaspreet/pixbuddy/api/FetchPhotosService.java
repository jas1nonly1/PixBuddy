package saini.jaspreet.pixbuddy.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchPhotosService {
    public static String API_KEY = "12580203-f0d5a69c8ebfcf58af445b523";
    public static PhotosApiConfig photosApiConfig(){
        Retrofit.Builder builder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com/");
        return builder.build().create(PhotosApiConfig.class);
    }
}
