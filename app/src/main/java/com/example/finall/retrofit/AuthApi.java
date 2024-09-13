package com.example.finall.retrofit;

import com.example.finall.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("api/auth/login")
    Call<User> authenticateUser(@Body User user);
}
