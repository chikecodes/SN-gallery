package com.spark.networks.coding.chike.di.component;

import android.app.Application;

import com.spark.networks.coding.chike.App;
import com.spark.networks.coding.chike.di.builder.ActivityBuilder;
import com.spark.networks.coding.chike.di.module.AppModule;
import com.spark.networks.coding.chike.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by chike on 11/22/18.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, NetworkModule.class,
        ActivityBuilder.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
