package com.example.deniz.exampleandroidapp.model.RestService;

import com.example.deniz.exampleandroidapp.model.Person;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by deniz.gokce on 30.11.2017.
 */

public interface PeopleService {
    @GET("people")
    Call<List<Person>> getPeople();

    @Headers("Content-Type: application/json")
    @POST("people")
    Call<List<Person>> createPerson(@Body JsonObject person);

    @DELETE("people/{id}")
    Call<List<Person>> deletePerson(@Path("id") int id);

    @GET("people/{id}")
    Call<Person> getPerson(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @PUT("people/{id}")
    Call<List<Person>> editPerson(@Path("id") int id, @Body JsonObject person);

}
