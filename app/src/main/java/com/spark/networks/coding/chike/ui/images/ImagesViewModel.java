package com.spark.networks.coding.chike.ui.images;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;

import com.spark.networks.coding.chike.base.BaseViewModel;
import com.spark.networks.coding.chike.constants.AppConstants;
import com.spark.networks.coding.chike.model.UploadImageRequest;
import com.spark.networks.coding.chike.model.UploadImageResponse;
import com.spark.networks.coding.chike.model.UploadedImageResponse;
import com.spark.networks.coding.chike.networking.Resource;
import com.spark.networks.coding.chike.repository.GalleryContract;
import com.spark.networks.coding.chike.rx.SchedulerProvider;
import com.spark.networks.coding.chike.utils.Utilities;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class ImagesViewModel extends BaseViewModel  {

    private final MutableLiveData<Resource<List<UploadedImageResponse>>> imagesLiveData;
    private final MutableLiveData<Resource<UploadImageResponse>> uploadImageLiveData;

    public ImagesViewModel(GalleryContract.Repository repository, SchedulerProvider schedulerProvider) {
        super(repository, schedulerProvider);
        imagesLiveData = new MutableLiveData<>();
        uploadImageLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Resource<List<UploadedImageResponse>>> getImagesLiveData() {
        return imagesLiveData;
    }

    public MutableLiveData<Resource<UploadImageResponse>> getUploadImageLiveData() {
        return uploadImageLiveData;
    }

    public void fetchImages() {
        getCompositeDisposable().add(getRepository()
                .getImages()
                .doOnSubscribe(response -> imagesLiveData.postValue(Resource.loading()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                            if (response != null && response.getUploads().size() > 0) {
                                imagesLiveData.setValue(Resource
                                        .success(response.getUploads()));
                            }
                        },
                        error -> imagesLiveData.setValue(Resource.error(error.getMessage()))));
    }

    public void uploadImageLiveData(Bitmap bitmap) {
        getCompositeDisposable().add(convertToBase64(bitmap)
                .flatMap(imageBase64 -> {
                    UploadImageRequest imageRequest = new UploadImageRequest(imageBase64);
                    return getRepository().uploadImage(imageRequest).toObservable();
                })
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

    private Observable<String> convertToBase64(Bitmap bitmap) {
        return Observable.fromCallable(() -> Utilities.Companion.convertBitmapToBase64(bitmap));
    }
}
