package com.spark.networks.coding.chike.networking

import com.spark.networks.coding.chike.model.GetImagesResponse
import com.spark.networks.coding.chike.model.UploadImageRequest
import com.spark.networks.coding.chike.model.UploadImageResponse
import com.spark.networks.coding.chike.model.UploadedImageResponse
import io.reactivex.Single
import retrofit2.http.*

interface WebService {

    @Headers("Content-Type: application/json")
    @POST(ApiEndpoint.ENDPOINT_UPLOADS)
    fun uploadImage(@Body request: UploadImageRequest): Single<UploadImageResponse>

    @Headers("Content-Type: application/json")
    @GET(ApiEndpoint.ENDPOINT_UPLOADS)
    fun getImages(): Single<GetImagesResponse>
}