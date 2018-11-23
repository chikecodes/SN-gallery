package com.spark.networks.coding.chike.ui.upload;

import com.spark.networks.coding.chike.data.repository.GalleryContract;
import com.spark.networks.coding.chike.rx.SchedulerProvider;
import com.spark.networks.coding.chike.utils.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by chike on 11/22/18.
 */
@Module
public class UploadImageActivityModule {

    @Provides
    UploadImageViewModel imagesViewModel(GalleryContract.Repository repository,
                                    SchedulerProvider schedulerProvider) {
        return new UploadImageViewModel(repository, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideRecipesViewModel(UploadImageViewModel imagesViewModel) {
        return new ViewModelProviderFactory<>(imagesViewModel);
    }
}
