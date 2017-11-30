package com.example.deniz.exampleandroidapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by deniz.gokce on 30.11.2017.
 */

public interface PeopleService {
    @GET("people")
    Call<List<Person>> getPeople();
}
