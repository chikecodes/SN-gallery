package com.spark.networks.coding.chike.data.networking

import com.spark.networks.coding.chike.data.model.UploadImageRequest
import com.spark.networks.coding.chike.testing.DependencyProvider
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(23))
class WebServiceTest {

    private lateinit var webService: WebService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun init() {
        mockWebServer = MockWebServer()
        webService = DependencyProvider
                .getRetrofit(mockWebServer.url("/"))
                .create(WebService::class.java)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun uploadImage() {
        queueResponse {
            setResponseCode(200)
            setBody(DependencyProvider.getResponseFromJson("upload_successful"))
        }

        val imageBase64 = "image-xxxx"
        val uploadImageRequest = UploadImageRequest(imageBase64)

        webService
                .uploadImage(uploadImageRequest)
                .test()
                .run {
                    assertNoErrors()
                    assertValueCount(1)
                    Assert.assertEquals(values()[0].uploadedImage.image, imageBase64)
                }
    }

    @Test
    fun getImages() {
        queueResponse {
            setResponseCode(200)
            setBody(DependencyProvider.getResponseFromJson("images"))
        }

        webService
                .getImages()
                .test()
                .run {
                    assertNoErrors()
                    assertValueCount(1)
                    Assert.assertEquals(values()[0].count, 3)
                    Assert.assertEquals(values()[0].uploads.size, 3)
                    Assert.assertEquals(values()[0].uploads[0].id, "5bf545c04610334492ad0c46")
                    Assert.assertEquals(values()[0].uploads[1].id, "5bf5468b06b28644df332acc")
                    Assert.assertEquals(values()[0].uploads[2].id, "5bf5469e24323044fb1ead69")
                }
    }

    private fun queueResponse(block: MockResponse.() -> Unit) {
        mockWebServer.enqueue(MockResponse().apply(block))
    }
}