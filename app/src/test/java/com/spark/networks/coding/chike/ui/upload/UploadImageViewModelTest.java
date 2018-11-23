package com.spark.networks.coding.chike.ui.upload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.google.gson.Gson;
import com.spark.networks.coding.chike.R;
import com.spark.networks.coding.chike.model.GetImagesResponse;
import com.spark.networks.coding.chike.model.UploadImageRequest;
import com.spark.networks.coding.chike.model.UploadImageResponse;
import com.spark.networks.coding.chike.networking.Status;
import com.spark.networks.coding.chike.repository.GalleryContract;
import com.spark.networks.coding.chike.testing.DependencyProvider;
import com.spark.networks.coding.chike.testing.TestSchedulerProvider;
import com.spark.networks.coding.chike.ui.images.ImagesViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;

/**
 * Created by chike on 11/22/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class UploadImageViewModelTest {

    @Mock
    GalleryContract.Repository repository;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private UploadImageViewModel uploadImageViewModel;
    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        testScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(testScheduler);
        uploadImageViewModel = new UploadImageViewModel(repository, testSchedulerProvider);
    }

    @After
    public void tearDown() {
        testScheduler = null;
        uploadImageViewModel = null;
    }

    @Test
    public void upLoadImagesTriggersSuccessState() {
        Gson gson = new Gson();
        UploadImageResponse response = gson.fromJson(DependencyProvider.getResponseFromJson("upload_successful"),
                UploadImageResponse.class);
        UploadImageRequest request = new UploadImageRequest("xxxxx");
        doReturn(Single.just(response))
                .when(repository).uploadImage(request);
        uploadImageViewModel.uploadImageLiveData(request);
        testScheduler.triggerActions();
        assert(uploadImageViewModel.getUploadImageLiveData().getValue().status == Status.SUCCESS);
    }
}