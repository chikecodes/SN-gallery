package com.spark.networks.coding.chike.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Utility functions
 **/
class Utilities {

    companion object {
        fun convertBitmapToBase64(bitmap: Bitmap?): String {
            val stream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 70, stream)
            val byteFormat = stream.toByteArray()
            return Base64.encodeToString(byteFormat, Base64.NO_WRAP)
        }
    }
}