package com.spark.networks.coding.chike.ui.images;

import com.spark.networks.coding.chike.repository.GalleryContract;
import com.spark.networks.coding.chike.rx.SchedulerProvider;
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
public class ImagesActivityModule {

    @Provides
    ImagesViewModel imagesViewModel(GalleryContract.Repository repository,
                                    SchedulerProvider schedulerProvider) {
        return new ImagesViewModel(repository, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ImagesActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    ImageAdapter provideImageAdapter() {
        return new ImageAdapter();
    }

    @Provides
    ViewModelProvider.Factory provideRecipesViewModel(ImagesViewModel imagesViewModel) {
        return new ViewModelProviderFactory<>(imagesViewModel);
    }
}
