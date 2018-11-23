package com.spark.networks.coding.chike.data.repository

import com.spark.networks.coding.chike.data.model.GetImagesResponse
import com.spark.networks.coding.chike.data.model.UploadImageRequest
import com.spark.networks.coding.chike.data.model.UploadImageResponse
import io.reactivex.Single

interface GalleryContract {

    interface Repository {

        fun uploadImage(request: UploadImageRequest): Single<UploadImageResponse>

        fun getImages(): Single<GetImagesResponse>

    }
}