package com.example.deniz.exampleandroidapp.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by deniz.gokce on 30.11.2017.
 */

public class PeopleRepository {

    PeopleService service;

    @Inject
    public PeopleRepository(PeopleService service) {
        this.service = service;
    }


    public LiveData<List<Person>> getPeople() {
        final MutableLiveData<List<Person>> data = new MutableLiveData<>();

        service.getPeople().enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<Person>> addPerson(Person person) {
        final MutableLiveData<List<Person>> data = new MutableLiveData<>();
        service.createPerson(person.getJson()).enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });
        return data;
    }
}
