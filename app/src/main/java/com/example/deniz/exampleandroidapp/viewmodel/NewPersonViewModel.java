package com.example.deniz.exampleandroidapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.deniz.exampleandroidapp.model.PeopleRepository;
import com.example.deniz.exampleandroidapp.model.Person;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Deniz on 10.12.2017.
 */

public class NewPersonViewModel extends ViewModel {
    private PeopleRepository repository;

    @Inject
    NewPersonViewModel(PeopleRepository repository) {
        this.repository = repository;
    }

    /**
     * Attach our LiveData to the Database
     */
    public void addNewPersonToDatabase(Person person) {
        new AddItemTask().execute(person);
    }

    private class AddItemTask extends AsyncTask<Person, Void, Void> {

        @Override
        protected Void doInBackground(Person... item) {
            repository.addPerson(item[0]);
            return null;
        }
    }
}
