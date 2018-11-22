package com.spark.networks.coding.chike.networking;

import com.spark.networks.coding.chike.model.GetImagesResponse;
import com.spark.networks.coding.chike.model.UploadImageRequest;
import com.spark.networks.coding.chike.model.UploadImageResponse;
import com.spark.networks.coding.chike.model.UploadedImageResponse;

import io.reactivex.Single;

/**
 * Created by chike  on 10/10/18.
 */
public interface ApiHelper {

    Single<UploadImageResponse> executeUploadImage(UploadImageRequest request);

    Single<GetImagesResponse> executeGetImages();
}
