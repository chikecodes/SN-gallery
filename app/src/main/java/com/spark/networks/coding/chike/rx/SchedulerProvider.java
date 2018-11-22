package com.spark.networks.coding.chike.rx;

import io.reactivex.Scheduler;

/**
 * Created by chike on 11/22/18.
 */
public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}