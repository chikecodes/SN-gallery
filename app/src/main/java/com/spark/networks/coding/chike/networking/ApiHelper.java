package com.spark.networks.coding.chike.networking;

import com.spark.networks.coding.chike.model.UploadImageRequest;
import com.spark.networks.coding.chike.model.UploadedImageResponse;

import io.reactivex.Single;

/**
 * Created by chike  on 10/10/18.
 */
public interface ApiHelper {

    Single<UploadedImageResponse> executeUploadImage(UploadImageRequest request);
}
