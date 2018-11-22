package com.spark.networks.coding.chike.networking;

import com.spark.networks.coding.chike.model.UploadImageRequest;
import com.spark.networks.coding.chike.model.UploadedImageResponse;

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
    public Single<UploadedImageResponse> executeUploadImage(UploadImageRequest request) {
        return service.uploadImage(request);
    }
}
