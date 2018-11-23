package com.spark.networks.coding.chike.data.model

import com.google.gson.annotations.SerializedName

data class UploadedImageResponse(@SerializedName("_id") val id: String,
                                 @SerializedName("time_uploaded") val timeUploaded: String,
                                 val image: String)