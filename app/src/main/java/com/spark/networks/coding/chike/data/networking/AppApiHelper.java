package com.spark.networks.coding.chike.data.networking;

import com.spark.networks.coding.chike.data.model.GetImagesResponse;
import com.spark.networks.coding.chike.data.model.UploadImageRequest;
import com.spark.networks.coding.chike.data.model.UploadImageResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    private WebService service;

    @Inject
    public AppApiHelper(WebService service) {
        this.service = service;
    }

    @Override
    public Single<UploadImageResponse> executeUploadImage(UploadImageRequest request) {
        return service.uploadImage(request);
    }

    @Override
    public Single<GetImagesResponse> executeGetImages() {
        return service.getImages();
    }
}
