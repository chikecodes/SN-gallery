package com.spark.networks.coding.chike.data.networking;

import com.spark.networks.coding.chike.data.model.GetImagesResponse;
import com.spark.networks.coding.chike.data.model.UploadImageRequest;
import com.spark.networks.coding.chike.data.model.UploadImageResponse;

import io.reactivex.Single;

/**
 * Created by chike  on 10/10/18.
 */
public interface ApiHelper {

    Single<UploadImageResponse> executeUploadImage(UploadImageRequest request);

    Single<GetImagesResponse> executeGetImages();
}
