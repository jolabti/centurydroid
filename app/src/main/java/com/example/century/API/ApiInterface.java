package com.example.century.API;

import com.example.century.GSON.GSONResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("auth")
    Call<GSONResponseLogin> postAuth(@Field("email") String email,
                                       @Field("password") String password);


}
