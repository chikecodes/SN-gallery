package com.spark.networks.coding.chike.testing

import androidx.annotation.VisibleForTesting
import com.spark.networks.coding.chike.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
class TestSchedulerProvider(private val testScheduler: TestScheduler) : SchedulerProvider {

    override fun computation(): Scheduler {
        return testScheduler
    }

    override fun ui(): Scheduler {
        return testScheduler
    }

    override fun io(): Scheduler {
        return testScheduler
    }
}