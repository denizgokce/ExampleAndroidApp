package com.example.deniz.exampleandroidapp.dependencyInjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Deniz on 30.11.2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class/*, RoomModule.class*/})
public interface ApplicationComponent {

    //void inject(ListFragment listFragment);
    //void inject(CreateFragment createFragment);
    //void inject(DetailFragment detailFragment);

    Application application();

}