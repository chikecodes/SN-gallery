package com.spark.networks.coding.chike.repository

import com.spark.networks.coding.chike.model.UploadImageRequest
import com.spark.networks.coding.chike.model.UploadImageResponse
import io.reactivex.Single

interface GalleryContract {

    interface Repository {

        fun uploadImage(request: UploadImageRequest): Single<UploadImageResponse>

    }
}