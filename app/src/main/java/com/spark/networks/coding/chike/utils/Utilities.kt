package com.spark.networks.coding.chike.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import java.io.ByteArrayOutputStream

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

        fun setBitmapToImageView(imageView: ImageView, base64: String) {
            val base64String = "data:image/png;base64,$base64"
            val base64Image = base64String.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            imageView.setImageBitmap(decodedByte)
        }
    }
}