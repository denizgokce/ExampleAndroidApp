package com.example.deniz.exampleandroidapp.model;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by deniz.gokce on 30.11.2017.
 */

public interface PeopleService {
    @GET("people")
    Call<List<Person>> getPeople();
    @Headers("Content-Type: application/json")
    @POST("people")
    Call<List<Person>> createPerson(@Body JsonObject person);
}
