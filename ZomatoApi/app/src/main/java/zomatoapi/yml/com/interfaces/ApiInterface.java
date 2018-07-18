package zomatoapi.yml.com.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import zomatoapi.yml.com.models.RestaurentCategory;

public interface ApiInterface {
    @GET("categories")
    Call<RestaurentCategory> getCategories(@Header("user-key") String key);
}
