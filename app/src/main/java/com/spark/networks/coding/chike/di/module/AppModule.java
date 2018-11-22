package com.spark.networks.coding.chike.di.module;

import android.app.Application;
import android.content.Context;

import com.spark.networks.coding.chike.repository.GalleryContract;
import com.spark.networks.coding.chike.repository.RepositoryImpl;
import com.spark.networks.coding.chike.rx.AppSchedulerProvider;
import com.spark.networks.coding.chike.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chike  on 11/22/18.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    GalleryContract.Repository provideRepository(RepositoryImpl repository) {
        return repository;
    }

}