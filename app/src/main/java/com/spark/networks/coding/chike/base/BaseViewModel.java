package com.spark.networks.coding.chike.base;

import android.arch.lifecycle.ViewModel;
import com.spark.networks.coding.chike.repository.GalleryContract;
import com.spark.networks.coding.chike.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chike on 11/22/18.
 */
public abstract class BaseViewModel extends ViewModel {

    private final GalleryContract.Repository repository;

    private final SchedulerProvider schedulerProvider;

    private CompositeDisposable compositeDisposable;

    public BaseViewModel(GalleryContract.Repository dataManager, SchedulerProvider schedulerProvider) {
        this.repository = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public GalleryContract.Repository getRepository() {
        return repository;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

}
