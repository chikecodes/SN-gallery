package  com.spark.networks.coding.chike.data.networking

import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.spark.networks.coding.chike.data.model.UploadImageRequest
import com.spark.networks.coding.chike.data.model.UploadImageResponse
import com.spark.networks.coding.chike.testing.DependencyProvider
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Tests for [AppApiHelper]
 */
@RunWith(RobolectricTestRunner::class)
class AppApiHelperTest {

    private val webService = mock<WebService>()

    @Test
    fun uploadImage() {
        val imageBase64 = "image-xxxx"
        val uploadImageRequest = UploadImageRequest(imageBase64)

        val gson = Gson()
        val response = gson.fromJson(DependencyProvider.getResponseFromJson("upload_successful"),
                UploadImageResponse::class.java)

        whenever(webService.uploadImage(uploadImageRequest)).thenReturn(
                Single.just(response)
        )

        AppApiHelper(webService).executeUploadImage(uploadImageRequest).test().run {
            assertNoErrors()
            assertValueCount(1)
            assertEquals(values()[0].uploadedImage.image, imageBase64)
        }
    }
}