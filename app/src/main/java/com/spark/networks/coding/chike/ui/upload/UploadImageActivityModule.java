package com.spark.networks.coding.chike.ui.upload;

import com.spark.networks.coding.chike.repository.GalleryContract;
import com.spark.networks.coding.chike.rx.SchedulerProvider;
import com.spark.networks.coding.chike.ui.images.ImagesActivity;
import com.spark.networks.coding.chike.ui.images.ImagesViewModel;
import com.spark.networks.coding.chike.ui.images.adapter.ImageAdapter;
import com.spark.networks.coding.chike.utils.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
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
