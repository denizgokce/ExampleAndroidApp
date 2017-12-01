package com.example.deniz.exampleandroidapp.dependencyInjection;

import com.example.deniz.exampleandroidapp.ExampleAndroidApp;
import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Deniz on 30.11.2017.
 */

@Module
public class ApplicationModule {
    private final ExampleAndroidApp application;

    public ApplicationModule(ExampleAndroidApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ExampleAndroidApp provideExampleAndroidAppApplication() {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}
