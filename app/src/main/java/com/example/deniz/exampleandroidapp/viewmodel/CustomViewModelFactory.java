package com.example.deniz.exampleandroidapp.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.deniz.exampleandroidapp.model.PeopleRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by deniz.gokce on 30.11.2017.
 */
@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final PeopleRepository repository;

    @Inject
    public CustomViewModelFactory(PeopleRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListViewModel.class))
            return (T) new ListViewModel(repository);

        else if (modelClass.isAssignableFrom(CreateViewModel.class))
            return (T) new CreateViewModel(repository);

        else if (modelClass.isAssignableFrom(EditViewModel.class))
            return (T) new EditViewModel(repository);

        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
