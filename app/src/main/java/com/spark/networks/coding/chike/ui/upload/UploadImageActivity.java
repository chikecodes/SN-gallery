package com.spark.networks.coding.chike.ui.upload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.spark.networks.coding.chike.R;
import com.spark.networks.coding.chike.base.BaseActivity;
import com.spark.networks.coding.chike.data.model.UploadImageRequest;
import com.spark.networks.coding.chike.utils.Utilities;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;

public class UploadImageActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UploadImageViewModel uploadImageViewModel;
    private ImageView imageSelectedImageView;
    private ProgressBar progressBar;
    private Button selectImageButton;

    public static void start(Context context) {
        Intent starter = new Intent(context, UploadImageActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        AndroidInjection.inject(this);
        uploadImageViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UploadImageViewModel.class);

        initViews();
    }

    private void initViews() {
        imageSelectedImageView = findViewById(R.id.iv_image);
        progressBar = findViewById(R.id.upload_pb);
        selectImageButton = findViewById(R.id.btn_select_image);

        selectImageButton.setOnClickListener(view ->
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(UploadImageActivity.this));

        initiateDataListener();
    }

    private void initiateDataListener() {
        uploadImageViewModel.getUploadImageLiveData().observe(this, result -> {
            if (result != null) {
                switch (result.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        imageSelectedImageView.setVisibility(View.GONE);
                        selectImageButton.setVisibility(View.GONE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        finish();
                        if (result.data != null) {
                            Toast.makeText(UploadImageActivity.this,
                                    "Upload successful", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(UploadImageActivity.this,
                                result.message, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                            resultUri);
                    imageSelectedImageView.setImageBitmap(bitmap);
                    selectImageButton.setText(R.string.action_upload_image);
                    selectImageButton.setOnClickListener(view -> {
                        UploadImageRequest request = new UploadImageRequest(Utilities.Companion
                                .convertBitmapToBase64(bitmap));
                        uploadImageViewModel.uploadImageLiveData(request);

                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}