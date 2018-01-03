package com.example.deniz.exampleandroidapp.dependencyInjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.example.deniz.exampleandroidapp.dependencyInjection.Components.RoomComponent;
import com.example.deniz.exampleandroidapp.dependencyInjection.Components.ServiceComponent;
import com.example.deniz.exampleandroidapp.model.LocalDatabase.PersonDao;
import com.example.deniz.exampleandroidapp.model.LocalDatabase.PersonDatabase;
import com.example.deniz.exampleandroidapp.model.PeopleRepository;
import com.example.deniz.exampleandroidapp.model.RestService.PeopleService;
import com.example.deniz.exampleandroidapp.viewmodel.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by deniz.gokce on 3.01.2018.
 */
@Module
public class MainModule {

    PeopleService service;
    PersonDatabase database;

    public MainModule(Application application) {
        this.database = (new RoomComponent(application)).getDatabase();
        this.service = (new ServiceComponent()).getService();
    }

    /***
     *
     * */
    @Provides
    @Singleton
    PeopleRepository providePeopleRepository() {
        return new PeopleRepository(service, database.personDao());
    }

    @Provides
    @Singleton
    PeopleService providePeopleService() {
        return this.service;
    }


    @Provides
    @Singleton
    PersonDao providePersonDao(PersonDatabase database) {
        return database.personDao();
    }

    @Provides
    @Singleton
    PersonDatabase providePersonDatabase(Application application) {
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(PeopleRepository repository) {
        return new CustomViewModelFactory(repository);
    }
}
