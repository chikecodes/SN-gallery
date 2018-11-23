package com.spark.networks.coding.chike.data.model

data class GetImagesResponse(val count: Int,
                             val uploads: ArrayList<UploadedImageResponse>)