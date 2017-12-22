package com.example.deniz.exampleandroidapp.dependencyInjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;


import com.example.deniz.exampleandroidapp.view.create.CreateFragment;
import com.example.deniz.exampleandroidapp.view.edit.EditFragment;
import com.example.deniz.exampleandroidapp.view.list.ListFragment;

/**
 * Created by Deniz on 30.11.2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class})
public interface ApplicationComponent {

    void inject(ListFragment listFragment);

    void inject(CreateFragment createFragment);

    void inject(EditFragment editFragment);

    Application application();
}