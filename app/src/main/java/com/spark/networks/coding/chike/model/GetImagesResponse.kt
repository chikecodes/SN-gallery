package com.spark.networks.coding.chike.model

data class GetImagesResponse(val count: Int,
                             val uploads: ArrayList<UploadedImageResponse>)