package com.spark.networks.coding.chike.ui.images;

import com.spark.networks.coding.chike.base.BaseViewModel;
import com.spark.networks.coding.chike.data.model.UploadedImageResponse;
import com.spark.networks.coding.chike.data.networking.Resource;
import com.spark.networks.coding.chike.data.repository.GalleryContract;
import com.spark.networks.coding.chike.rx.SchedulerProvider;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class ImagesViewModel extends BaseViewModel  {

    private final MutableLiveData<Resource<List<UploadedImageResponse>>> imagesLiveData;

    public ImagesViewModel(GalleryContract.Repository repository, SchedulerProvider schedulerProvider) {
        super(repository, schedulerProvider);
        imagesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Resource<List<UploadedImageResponse>>> getImagesLiveData() {
        return imagesLiveData;
    }

    public void fetchImages() {
        getCompositeDisposable().add(getRepository()
                .getImages()
                .doOnSubscribe(response -> imagesLiveData.postValue(Resource.loading()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                            if (response != null) {
                                imagesLiveData.setValue(Resource
                                        .success(response.getUploads()));
                            }
                        },
                        error -> imagesLiveData.setValue(Resource.error(error.getMessage()))));
    }
}
