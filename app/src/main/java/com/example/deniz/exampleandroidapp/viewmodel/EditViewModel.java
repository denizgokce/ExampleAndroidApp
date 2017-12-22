package com.example.deniz.exampleandroidapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.deniz.exampleandroidapp.model.PeopleRepository;
import com.example.deniz.exampleandroidapp.model.Person;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by deniz.gokce on 22.12.2017.
 */

public class EditViewModel extends ViewModel {
    private PeopleRepository repository;
    public LiveData<Person> person;

    @Inject
    EditViewModel(PeopleRepository repository) {
        this.repository = repository;
    }

    public LiveData<Person> getPerson(int id) {
        return repository.getPerson(id);
    }

    public void editPerson(Person person) {
        EditViewModel.EditPersonTask editPersonTask = new EditViewModel.EditPersonTask();
        editPersonTask.execute(person);
    }

    private class EditPersonTask extends AsyncTask<Person, Void, Void> {

        @Override
        protected Void doInBackground(Person... item) {
            repository.editPerson(item[0]);
            return null;
        }
    }
}
