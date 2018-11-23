package com.spark.networks.coding.chike.ui.images.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spark.networks.coding.chike.R;
import com.spark.networks.coding.chike.base.BaseViewHolder;
import com.spark.networks.coding.chike.data.model.UploadedImageResponse;
import com.spark.networks.coding.chike.utils.Utilities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ImageAdapter extends ListAdapter<UploadedImageResponse, ImageAdapter.ImagesViewHolder> {

    public ImageAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<UploadedImageResponse> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UploadedImageResponse>() {
                @Override
                public boolean areItemsTheSame(@NonNull UploadedImageResponse image1,
                                               @NonNull UploadedImageResponse image2) {
                    return image1.getId().equals(image2.getId());
                }

                public boolean areContentsTheSame(@NonNull UploadedImageResponse image1,
                                                  @NonNull UploadedImageResponse image2) {
                    return image1.equals(image2);
                }
            };

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ImagesViewHolder(inflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        Utilities.Companion.setBitmapToImageView(holder.imageView, getItem(position).getImage());
    }

    static class ImagesViewHolder extends BaseViewHolder {
        private ImageView imageView;
        ImagesViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }
}
