package com.spark.networks.coding.chike.ui.images;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.spark.networks.coding.chike.R;
import com.spark.networks.coding.chike.base.BaseActivity;
import com.spark.networks.coding.chike.events.RefreshEvent;
import com.spark.networks.coding.chike.ui.images.adapter.ImageAdapter;
import com.spark.networks.coding.chike.ui.upload.UploadImageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dagger.android.AndroidInjection;

public class ImagesActivity extends BaseActivity {

    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    ImageAdapter imageAdapter;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ImagesViewModel imagesViewModel;
    private RecyclerView imagesRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, ImagesActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        AndroidInjection.inject(this);
        imagesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ImagesViewModel.class);

        initViews();
    }

    private void initViews() {
        imagesRecyclerView = findViewById(R.id.rv_images);
        swipeRefreshLayout = findViewById(R.id.srl_images);

        imagesRecyclerView.setAdapter(imageAdapter);
        imagesRecyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(() -> imagesViewModel.fetchImages());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> UploadImageActivity.start(ImagesActivity.this));

        imagesViewModel.fetchImages();
        initiateDataListener();
    }

    private void initiateDataListener() {
        imagesViewModel.getImagesLiveData().observe(this, listResource -> {
            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING:
                        swipeRefreshLayout.setRefreshing(true);
                        break;
                    case SUCCESS:
                        swipeRefreshLayout.setRefreshing(false);
                        if (listResource.data != null) {
                            if (listResource.data.size() > 0) {
                                imageAdapter.submitList(listResource.data);
                            }

                            if (listResource.data.size() == 0) {
                                imagesRecyclerView.setVisibility(View.GONE);
                                Toast.makeText(ImagesActivity.this,
                                        R.string.mssg_no_image, Toast.LENGTH_LONG).show();
                            }
                        }
                        break;
                    case ERROR:
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(ImagesActivity.this,
                                listResource.message, Toast.LENGTH_LONG).show();
                        break;
                }
            }

        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshEvent event) {
        if (event != null) {
            imagesViewModel.fetchImages();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeStickyEvents();
    }

    private void removeStickyEvents() {
        RefreshEvent refreshEvent = EventBus.getDefault().getStickyEvent(RefreshEvent.class);
        if (refreshEvent != null) {
            EventBus.getDefault().removeStickyEvent(refreshEvent);
        }
    }
}