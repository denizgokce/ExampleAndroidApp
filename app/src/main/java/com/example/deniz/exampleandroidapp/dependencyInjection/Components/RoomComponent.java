package com.example.deniz.exampleandroidapp.dependencyInjection.Components;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.example.deniz.exampleandroidapp.model.LocalDatabase.PersonDao;
import com.example.deniz.exampleandroidapp.model.LocalDatabase.PersonDatabase;
import com.example.deniz.exampleandroidapp.model.PeopleRepository;
import com.example.deniz.exampleandroidapp.viewmodel.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by deniz.gokce on 3.01.2018.
 */

public class RoomComponent {

    public PersonDatabase getDatabase() {
        return database;
    }

    private final PersonDatabase database;

    public RoomComponent(Application application) {
        this.database = Room.databaseBuilder(
                application,
                PersonDatabase.class,
                "Person.db"
        ).build();
    }


}
