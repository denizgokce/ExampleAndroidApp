package com.example.deniz.exampleandroidapp.dependencyInjection.Components;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.example.deniz.exampleandroidapp.model.RestService.PeopleService;
import com.example.deniz.exampleandroidapp.model.PeopleRepository;
import com.example.deniz.exampleandroidapp.viewmodel.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by deniz.gokce on 30.11.2017.
 */


public class ServiceComponent {

    public PeopleService getService() {
        return service;
    }

    private final PeopleService service;

    public ServiceComponent() {
        String API_BASE_URL = "https://rest-api-example-go.herokuapp.com/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        this.service = retrofit.create(PeopleService.class);
    }



}
