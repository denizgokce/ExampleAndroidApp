package com.example.deniz.exampleandroidapp;

import android.app.Application;

import com.example.deniz.exampleandroidapp.dependencyInjection.MainModule;
import com.example.deniz.exampleandroidapp.dependencyInjection.ApplicationComponent;
import com.example.deniz.exampleandroidapp.dependencyInjection.ApplicationModule;
import com.example.deniz.exampleandroidapp.dependencyInjection.DaggerApplicationComponent;

/**
 * Created by Deniz on 30.11.2017.
 */

public class ExampleAndroidApp extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .mainModule(new MainModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
