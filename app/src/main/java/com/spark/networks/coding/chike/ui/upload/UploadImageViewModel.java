package com.spark.networks.coding.chike.ui.upload;

import com.spark.networks.coding.chike.base.BaseViewModel;
import com.spark.networks.coding.chike.data.model.UploadImageRequest;
import com.spark.networks.coding.chike.data.model.UploadImageResponse;
import com.spark.networks.coding.chike.data.networking.Resource;
import com.spark.networks.coding.chike.data.repository.GalleryContract;
import com.spark.networks.coding.chike.rx.SchedulerProvider;

import androidx.lifecycle.MutableLiveData;

public class UploadImageViewModel extends BaseViewModel  {

    private final MutableLiveData<Resource<UploadImageResponse>> uploadImageLiveData;

    public UploadImageViewModel(GalleryContract.Repository repository, SchedulerProvider schedulerProvider) {
        super(repository, schedulerProvider);
        uploadImageLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Resource<UploadImageResponse>> getUploadImageLiveData() {
        return uploadImageLiveData;
    }

    public void uploadImageLiveData(UploadImageRequest request) {

        getCompositeDisposable().add(getRepository().uploadImage(request)
                .doOnSubscribe(response -> uploadImageLiveData.postValue(Resource.loading()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                            if (response != null) {
                                uploadImageLiveData.setValue(Resource.success(response));
                            }
                        },
                        error -> uploadImageLiveData.setValue(Resource.error(error.getMessage()))));
    }
}
