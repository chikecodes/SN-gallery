package com.spark.networks.coding.chike.data.networking

import com.spark.networks.coding.chike.data.model.GetImagesResponse
import com.spark.networks.coding.chike.data.model.UploadImageRequest
import com.spark.networks.coding.chike.data.model.UploadImageResponse
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