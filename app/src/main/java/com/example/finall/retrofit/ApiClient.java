package com.example.finall.retrofit;

import com.example.finall.retrofit.ProductApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //192.168.0.149
    //192.168.0.239
    private static final String BASE_URL = "http://192.168.0.149:8989/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ProductApi getProductApi() {
        return getRetrofitInstance().create(ProductApi.class);
    }
    public static UserApi getUserApi() {
        return getRetrofitInstance().create(UserApi.class);
    }
}
