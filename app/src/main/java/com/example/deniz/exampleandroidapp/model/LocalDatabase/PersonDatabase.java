package com.example.deniz.exampleandroidapp.model.LocalDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.deniz.exampleandroidapp.model.Person;

/**
 * Created by deniz.gokce on 3.01.2018.
 */

@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    public abstract PersonDao personDao();

}

