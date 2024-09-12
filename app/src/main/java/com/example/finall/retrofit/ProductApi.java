package com.example.finall.retrofit;
import com.example.finall.models.*;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductApi {
    @GET("api/products")
    Call<List<Product>> getProducts();

    @GET("api/products/{id}")
    Call<Product> getProductById(@Path("id") Long id);

    @POST("api/products")
    Call<Product> createProduct(@Body Product product);

    @PUT("api/products/{id}")
    Call<Product> updateProduct(@Path("id") Long id, @Body Product product);

    @DELETE("api/products/{id}")
    Call<Void> deleteProduct(@Path("id") Long id);
}
