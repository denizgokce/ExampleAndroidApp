package com.example.deniz.exampleandroidapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.deniz.exampleandroidapp.model.PeopleRepository;
import com.example.deniz.exampleandroidapp.model.Person;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by deniz.gokce on 30.11.2017.
 */

public class ListViewModel extends ViewModel {

    private PeopleRepository repository;
    @Inject
    ListViewModel(PeopleRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Person>> getListItems() {
        return repository.getPeople();
    }

    /*public void deleteListItem(ListItem listItem) {
        DeleteItemTask deleteItemTask = new DeleteItemTask();
        deleteItemTask.execute(listItem);
    }

    private class DeleteItemTask extends AsyncTask<ListItem, Void, Void> {

        @Override
        protected Void doInBackground(ListItem... item) {
            repository.deleteListItem(item[0]);
            return null;
        }
    }*/
}
