package com.example.deniz.exampleandroidapp.dependencyInjection;

import android.app.Application;

import com.example.deniz.exampleandroidapp.model.PeopleService;
import com.example.deniz.exampleandroidapp.model.PeopleRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by deniz.gokce on 30.11.2017.
 */

@Module
public class ServiceModule {

    private final PeopleService service;

    public ServiceModule(Application application) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api-example-go.herokuapp.com/")
                .build();
        this.service = retrofit.create(PeopleService.class);
    }

    @Provides
    @Singleton
    PeopleService providePeopleService(PeopleService service) {
        return new PeopleRepository(service);
    }

    /*@Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(ListItemRepository repository){
        return new CustomViewModelFactory(repository);
    }*/
}
