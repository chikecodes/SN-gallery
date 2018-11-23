package com.spark.networks.coding.chike.di.builder;

import com.spark.networks.coding.chike.ui.images.ImagesActivity;
import com.spark.networks.coding.chike.ui.images.ImagesActivityModule;
import com.spark.networks.coding.chike.ui.upload.UploadImageActivity;
import com.spark.networks.coding.chike.ui.upload.UploadImageActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by chike on 11/22/18.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = ImagesActivityModule.class)
    abstract ImagesActivity bindImagesActivity();


    @ContributesAndroidInjector(modules = UploadImageActivityModule.class)
    abstract UploadImageActivity bindUploadImageActivity();

}
