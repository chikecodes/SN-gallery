package com.spark.networks.coding.chike.ui.images;

import com.google.gson.Gson;
import com.spark.networks.coding.chike.data.model.GetImagesResponse;
import com.spark.networks.coding.chike.data.networking.Status;
import com.spark.networks.coding.chike.data.repository.GalleryContract;
import com.spark.networks.coding.chike.testing.DependencyProvider;
import com.spark.networks.coding.chike.testing.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;

/**
 * Created by chike on 11/22/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class ImagesViewModelTest {

    @Mock
    GalleryContract.Repository repository;
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private ImagesViewModel imagesViewModel;
    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        testScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(testScheduler);
        imagesViewModel = new ImagesViewModel(repository, testSchedulerProvider);
    }

    @After
    public void tearDown() {
        testScheduler = null;
        imagesViewModel = null;
    }

    @Test
    public void fetchImagesTriggersLoadedState() {
        Gson gson = new Gson();
        GetImagesResponse response = gson.fromJson(DependencyProvider.getResponseFromJson("images"),
                GetImagesResponse.class);
        doReturn(Single.just(response))
                .when(repository).getImages();
        imagesViewModel.fetchImages();
        testScheduler.triggerActions();
        assert(imagesViewModel.getImagesLiveData().getValue().status == Status.SUCCESS);
    }
}