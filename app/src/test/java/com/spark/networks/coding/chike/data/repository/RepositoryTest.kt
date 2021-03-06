package com.spark.networks.coding.chike.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.spark.networks.coding.chike.data.model.UploadImageRequest
import com.spark.networks.coding.chike.data.networking.ApiHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Tests for [RepositoryImpl]
 * */
@RunWith(RobolectricTestRunner::class)
class RepositoryTest {
    private lateinit var repository: GalleryContract.Repository
    private val remote: ApiHelper = mock()

    @Before
    fun init() {
        repository = RepositoryImpl(remote)
    }

    /**
     * Verify if calling [RepositoryImpl.uploadImage] triggers [ApiHelper.executeUploadImage]
     * */
    @Test
    fun testUploadImage() {
        val imageBase64 = "image-xxxx"
        val uploadImageRequest = UploadImageRequest(imageBase64)
        repository.uploadImage(uploadImageRequest)
        verify(remote).executeUploadImage(uploadImageRequest)
    }

    /**
     * Verify if calling [RepositoryImpl.getImages] triggers [ApiHelper.executeGetImages]
     * */
    @Test
    fun testGetImages() {
        repository.getImages()
        verify(remote).executeGetImages()
    }
}