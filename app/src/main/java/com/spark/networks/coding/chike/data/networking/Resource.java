package com.spark.networks.coding.chike.data.networking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by chike on 11/22/18.
 */
public class Resource<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable public final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg) {
        return new Resource<>(Status.ERROR, null, msg);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }

}
