package com.spark.networks.coding.chike.data.repository;

import com.spark.networks.coding.chike.data.model.GetImagesResponse;
import com.spark.networks.coding.chike.data.model.UploadImageRequest;
import com.spark.networks.coding.chike.data.model.UploadImageResponse;
import com.spark.networks.coding.chike.data.networking.ApiHelper;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Single;

public class RepositoryImpl implements GalleryContract.Repository {

    private final ApiHelper apiHelper;

    @Inject
    public RepositoryImpl(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @NotNull
    @Override
    public Single<UploadImageResponse> uploadImage(@NotNull UploadImageRequest request) {
        return apiHelper.executeUploadImage(request);
    }

    @NotNull
    @Override
    public Single<GetImagesResponse> getImages() {
        return apiHelper.executeGetImages();
    }
}